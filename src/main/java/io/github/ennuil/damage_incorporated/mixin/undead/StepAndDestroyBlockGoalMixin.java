package io.github.ennuil.damage_incorporated.mixin.undead;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.StepAndDestroyBlockGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(StepAndDestroyBlockGoal.class)
public abstract class StepAndDestroyBlockGoalMixin {
	@Shadow
	@Final
	private MobEntity stepAndDestroyMob;

	@WrapOperation(
		method = "canStart",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		)
	)
	private boolean di$modifyUndeadStepAndDestroy(GameRules gameRules, Key<BooleanRule> booleanRule, Operation<Boolean> original) {
		EntityType<?> mobType = this.stepAndDestroyMob.getType();
		GameRules.Key<BooleanRule> diGameRule = null;
		if (mobType.equals(EntityType.ZOMBIE)) {
			diGameRule = DIGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIES;
		} else if (mobType.equals(EntityType.ZOMBIFIED_PIGLIN)) {
			diGameRule = DIGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIFIED_PIGLINS;
		} else if (mobType.equals(EntityType.ZOMBIE_VILLAGER)) {
			diGameRule = DIGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIE_VILLAGERS;
		} else if (mobType.equals(EntityType.HUSK)) {
			diGameRule = DIGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_HUSKS;
		} else if (mobType.equals(EntityType.DROWNED)) {
			diGameRule = DIGameRules.CAN_TURTLE_EGGS_BE_STOMPED_BY_DROWNEDS;
		}

		return original.call(gameRules, booleanRule) && (diGameRule != null && gameRules.getBoolean(diGameRule));
	}
}
