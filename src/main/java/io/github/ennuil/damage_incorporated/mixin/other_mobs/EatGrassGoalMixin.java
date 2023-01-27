package io.github.ennuil.damage_incorporated.mixin.other_mobs;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.entity.ai.goal.EatGrassGoal;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(EatGrassGoal.class)
public abstract class EatGrassGoalMixin {
	@WrapOperation(
		method = "tick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z",
			ordinal = 0
		)
	)
	private boolean di$modifySheepGrassBreaking(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> booleanRule, Operation<Boolean> original) {
		return original.call(gameRules, booleanRule) && gameRules.getBoolean(DIGameRules.CAN_SHEEP_BREAK_GRASS);
	}

	@WrapOperation(
		method = "tick",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z",
			ordinal = 1
		)
	)
	private boolean di$modifySheepGrassBlockDirting(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> booleanRule, Operation<Boolean> original) {
		return original.call(gameRules, booleanRule) && gameRules.getBoolean(DIGameRules.CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT);
	}
}
