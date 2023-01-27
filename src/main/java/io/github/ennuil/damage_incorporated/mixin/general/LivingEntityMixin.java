package io.github.ennuil.damage_incorporated.mixin.general;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;
import net.minecraft.world.GameRules.Key;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(LivingEntity.class)
public abstract class LivingEntityMixin {
	@WrapOperation(method = "onKilledBy", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/GameRules;getBoolean(Lnet/minecraft/world/GameRules$Key;)Z"))
	private boolean di$modifyWitherRoseSpawning(GameRules gameRules, Key<BooleanRule> booleanRule, Operation<Boolean> original) {
		return original.call(gameRules, booleanRule) && gameRules.getBoolean(DIGameRules.CAN_WITHER_ROSE_BE_PLANTED);
	}
}
