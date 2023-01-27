package io.github.ennuil.damage_incorporated.mixin.other_mobs;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.entity.ai.brain.task.FarmerVillagerTask;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FarmerVillagerTask.class)
public abstract class FarmerVillagerTaskMixin {
	@WrapOperation(
		method = "shouldRun",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"
		)
	)
	private boolean di$modifyFarmerFarming(GameRules gameRules, GameRules.Key<GameRules.BooleanRule> booleanRule, Operation<Boolean> original) {
		return original.call(gameRules, booleanRule) && gameRules.getBoolean(DIGameRules.CAN_FARMER_VILLAGERS_FARM);
	}
}
