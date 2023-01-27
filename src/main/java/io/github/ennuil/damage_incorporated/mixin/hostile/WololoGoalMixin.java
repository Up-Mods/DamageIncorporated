package io.github.ennuil.damage_incorporated.mixin.hostile;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.entity.mob.EvokerEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EvokerEntity.WololoGoal.class)
public abstract class WololoGoalMixin {
	@WrapOperation(
		method = "canStart",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		)
	)
	private boolean di$modifyWololo(GameRules gameRules, Key<BooleanRule> booleanRule, Operation<Boolean> original) {
		return original.call(gameRules, booleanRule) && gameRules.getBoolean(DIGameRules.CAN_EVOKERS_WOLOLO);
	}
}
