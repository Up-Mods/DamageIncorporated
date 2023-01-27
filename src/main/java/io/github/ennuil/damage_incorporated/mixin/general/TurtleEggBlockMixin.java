package io.github.ennuil.damage_incorporated.mixin.general;

import io.github.ennuil.damage_incorporated.game_rules.DIEnums.AllowedEntities;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyConstant;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TurtleEggBlock.class)
public abstract class TurtleEggBlockMixin {
	@Unique
	private AllowedEntities di$storedGameRuleValue;

	@Inject(method = "breaksEgg(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Z", at = @At("HEAD"))
	private void getBreaksEggArgs(World world, Entity entity, CallbackInfoReturnable<Boolean> cir) {
		this.di$storedGameRuleValue = world.getGameRules().get(DIGameRules.TURTLE_EGG_TRAMPLING).get();
	}

	@ModifyConstant(
		method = "breaksEgg(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Z",
		constant = @Constant(classValue = LivingEntity.class)
	)
	private Class<?> modifyTurtleEggEntityConstant(Object object, Class<?> originalClass) {
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
		method = "breaksEgg(Lnet/minecraft/world/World;Lnet/minecraft/entity/Entity;)Z",
		constant = @Constant(classValue = PlayerEntity.class)
	)
	private Class<?> di$modifyTurtleEggPlayerConstant(Object object, Class<?> originalClass) {
		if (this.di$storedGameRuleValue == AllowedEntities.MOB_ONLY) {
			return Integer.class;
		}

		return originalClass;
	}
}
