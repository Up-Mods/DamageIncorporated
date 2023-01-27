package io.github.ennuil.damage_incorporated.mixin.general;

import io.github.ennuil.damage_incorporated.game_rules.DIEnums.AllowedEntities;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.block.BlockState;
import net.minecraft.block.FarmlandBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(FarmlandBlock.class)
public abstract class FarmlandBlockMixin {
	@Unique
	private AllowedEntities di$storedGameRuleValue;

	@Inject(
		method = "onLandedUpon",
		at = @At("HEAD")
	)
	private void getOnLandedUponArgs(World world, BlockState state, BlockPos pos, Entity entity, float fallDistance, CallbackInfo ci) {
		this.di$storedGameRuleValue = world.getGameRules().get(DIGameRules.FARMLAND_TRAMPLING).get();
	}

	@ModifyConstant(
		method = "onLandedUpon",
		constant = @Constant(classValue = LivingEntity.class)
	)
	private Class<?> di$modifyFarmlandEntityConstant(Object object, Class<?> originalClass) {
		return switch (this.di$storedGameRuleValue) {
			case PLAYER_ONLY -> PlayerEntity.class;
			case MOB_ONLY -> {
				if (object instanceof PlayerEntity) {
					yield Integer.class;
				} else {
					yield originalClass;
				}
			}
			case OFF -> Integer.class;
			default -> originalClass;
		};
	}

	@ModifyConstant(
		method = "onLandedUpon",
		constant = @Constant(classValue = PlayerEntity.class))
	private Class<?> di$modifyFarmlandPlayerConstant(Object object, Class<?> originalClass) {
		return switch (this.di$storedGameRuleValue) {
			case MOB_ONLY -> Integer.class;
			default -> originalClass;
		};
	}
}
