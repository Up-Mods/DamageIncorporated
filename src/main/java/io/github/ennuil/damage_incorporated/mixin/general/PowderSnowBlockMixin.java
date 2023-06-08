package io.github.ennuil.damage_incorporated.mixin.general;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIEnums.AllowedEntities;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.block.PowderSnowBlock;
import net.minecraft.entity.Entity;
import net.minecraft.entity.player.PlayerEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(PowderSnowBlock.class)
public abstract class PowderSnowBlockMixin {
	@WrapOperation(
		method = "onEntityCollision",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/entity/Entity;isOnFire()Z"
		)
	)
	private boolean di$modifyEntityFilter(Entity entity, Operation<Boolean> original) {
		var gameRuleValue = entity.getWorld().getGameRules().get(DIGameRules.CAN_BURNING_MOBS_BREAK_POWDER_SNOW).get();
		boolean allCheck = gameRuleValue.equals(AllowedEntities.ALL);
		boolean playerCheck = gameRuleValue.equals(AllowedEntities.PLAYER_ONLY) && entity instanceof PlayerEntity;
		boolean mobCheck = gameRuleValue.equals(AllowedEntities.MOB_ONLY) && !(entity instanceof PlayerEntity);

		return original.call(entity) && (allCheck || playerCheck || mobCheck);
	}
}
