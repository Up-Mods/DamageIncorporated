package io.github.ennuil.damage_incorporated.mixin.undead;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.entity.ai.goal.BreakDoorGoal;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(BreakDoorGoal.class)
public abstract class BreakDoorGoalMixin {
    @WrapOperation(
		method = "canStart",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		)
	)
	private boolean di$modifyDoorBreak(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> booleanRule, Operation<Boolean> original) {
		return original.call(gameRules, booleanRule) && gameRules.getBoolean(DIGameRules.CAN_MOBS_BREAK_DOORS);
	}
}
