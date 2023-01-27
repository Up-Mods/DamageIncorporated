package io.github.ennuil.damage_incorporated.utils;

import io.github.ennuil.damage_incorporated.game_rules.DIEnums;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import net.fabricmc.fabric.api.gamerule.v1.rule.EnumRule;
import net.minecraft.world.GameRules;

public class DIConversionUtils {
	public enum GameRuleConverters {
		CREEPER_DESTRUCTION_TYPE(
			"creeperDestructionType",
			new ExplosiveType(DIGameRules.CREEPER_EXPLOSIONS, DIGameRules.CREEPER_EXPLOSION_DROP_DECAY)
		),
		CHARGED_CREEPER_DESTRUCTION_TYPE(
			"chargedCreeperDestructionType",
			new InheritageExplosiveType(DIGameRules.CHARGED_CREEPER_EXPLOSIONS, DIGameRules.CHARGED_CREEPER_EXPLOSION_DROP_DECAY)
		),
		CAN_GHAST_FIREBALLS_SPREAD_FIRE(
			"canGhastFireballsSpreadFire",
			new BooleanType(DIGameRules.GHAST_FIREBALL_EXPLOSION_FIRE_SPREAD)
		),
		GHAST_FIREBALL_DESTRUCTION_TYPE(
			"ghastFireballDestructionType",
			new ExplosiveType(DIGameRules.GHAST_FIREBALL_EXPLOSIONS, DIGameRules.GHAST_FIREBALL_EXPLOSION_DROP_DECAY)
		),
		WITHER_SPAWN_DESTRUCTION_TYPE(
			"witherSpawnDestructionType",
			new ExplosiveType(DIGameRules.WITHER_SPAWN_EXPLOSIONS, DIGameRules.WITHER_SPAWN_EXPLOSION_DROP_DECAY)
		),
		WITHER_SKULL_DESTRUCTION_TYPE(
			"witherSkullDestructionType",
			new ExplosiveType(DIGameRules.WITHER_SKULL_EXPLOSIONS, DIGameRules.WITHER_SKULL_EXPLOSION_DROP_DECAY)
		),
		CAN_BLAZE_FIREBALLS_SPREAD_FIRE(
			"canBlazeFireballsSpreadFire",
			new BooleanType(DIGameRules.BLAZE_FIREBALL_FIRE_SPREAD)
		),
		CAN_PIGLINS_GATHER(
			"canPiglinsGather",
			new BooleanType(DIGameRules.CAN_PIGLINS_PICK_UP_ITEMS)
		);

		private final String gameRuleName;
		private final ConverterType type;

		GameRuleConverters(String gameRuleName, ConverterType type) {

			this.gameRuleName = gameRuleName;
			this.type = type;
		}

		public String getGameRuleName() {
			return gameRuleName;
		}

		public ConverterType getType() {
			return type;
		}
	}

	public interface ConverterType {}

	public record ExplosiveType(
			GameRules.Key<EnumRule<DIEnums.DIDestructionType>> explosionRule,
			GameRules.Key<GameRules.BooleanRule> dropDecayRule
	) implements ConverterType {}

	public record InheritageExplosiveType(
			GameRules.Key<EnumRule<DIEnums.DIDestructionTypeWithInheritage>> explosionRule,
			GameRules.Key<EnumRule<DIEnums.InheritageTristate>> dropDecayRule
	) implements ConverterType {}

	public record BooleanType(
			GameRules.Key<GameRules.BooleanRule> booleanRule
	) implements ConverterType {}
}
