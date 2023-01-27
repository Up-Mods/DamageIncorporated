package io.github.ennuil.damage_incorporated.mixin.conversion;

import com.mojang.serialization.DynamicLike;
import io.github.ennuil.damage_incorporated.utils.DIConversionUtils;
import io.github.ennuil.damage_incorporated.game_rules.DIEnums;
import net.fabricmc.fabric.api.gamerule.v1.rule.EnumRule;
import net.minecraft.world.GameRules;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(GameRules.class)
public abstract class GameRulesMixin {
	@Shadow
	@Final
	private Map<GameRules.Key<?>, GameRules.Rule<?>> rules;

	@SuppressWarnings("unchecked")
	@Inject(method = "load", at = @At("HEAD"))
	private void di$warnOnRenamedRules(DynamicLike<?> rules, CallbackInfo ci) {
		for (var converter : DIConversionUtils.GameRuleConverters.values()) {
			rules.get(converter.getGameRuleName()).asString().result().ifPresent(result -> {
				if (converter.getType() instanceof DIConversionUtils.BooleanType booleanTest) {
					var rule = (GameRules.BooleanRule) this.rules.get(booleanTest.booleanRule());
					rule.set(Boolean.parseBoolean(result), null);
				} else if (converter.getType() instanceof DIConversionUtils.ExplosiveType explosiveTest) {
					var explosionRule = (EnumRule<DIEnums.DIDestructionType>) this.rules.get(explosiveTest.explosionRule());
					var dropDecayRule = (GameRules.BooleanRule) this.rules.get(explosiveTest.dropDecayRule());

					explosionRule.set(DIEnums.convertOldToNewDestructionType(result), null);
					dropDecayRule.set(DIEnums.convertOldToNewDropDecay(result), null);
				} else if (converter.getType() instanceof DIConversionUtils.InheritageExplosiveType inheritageExplosiveTest) {
					var explosionRule = (EnumRule<DIEnums.DIDestructionTypeWithInheritage>) this.rules.get(inheritageExplosiveTest.explosionRule());
					var dropDecayRule = (EnumRule<DIEnums.InheritageTristate>) this.rules.get(inheritageExplosiveTest.dropDecayRule());

					explosionRule.set(DIEnums.convertOldToNewInheritageDestructionType(result), null);
					dropDecayRule.set(DIEnums.convertOldToNewInheritageDropDecay(result), null);
				}
			});
		}
	}
}
