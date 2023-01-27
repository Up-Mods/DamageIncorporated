package io.github.ennuil.damage_incorporated.game_rules;

import io.github.ennuil.damage_incorporated.game_rules.DIEnums.AllowedEntities;
import io.github.ennuil.damage_incorporated.game_rules.DIEnums.DIDestructionType;
import io.github.ennuil.damage_incorporated.game_rules.DIEnums.DIDestructionTypeWithInheritage;
import io.github.ennuil.damage_incorporated.game_rules.DIEnums.InheritageTristate;
import net.fabricmc.fabric.api.gamerule.v1.CustomGameRuleCategory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleFactory;
import net.fabricmc.fabric.api.gamerule.v1.GameRuleRegistry;
import net.fabricmc.fabric.api.gamerule.v1.rule.EnumRule;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.world.GameRules;
import net.minecraft.world.GameRules.BooleanRule;

public class DIGameRules {
	// Categories
	public static CustomGameRuleCategory DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "explosions_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.explosions").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	public static CustomGameRuleCategory DAMAGE_INCORPORATED_GENERAL_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "general_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.general").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	public static CustomGameRuleCategory DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "hostile_mobs_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.hostile_mobs").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	public static CustomGameRuleCategory DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "undead_mobs_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.undead_mobs").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	public static CustomGameRuleCategory DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY = new CustomGameRuleCategory(
		new Identifier("damage_incorporated", "passive_gamerules"),
		Text.translatable("damage_incorporated.gamerule.category.passive_mobs").formatted(Formatting.BOLD, Formatting.DARK_AQUA)
	);

	// Explosions
	public static GameRules.Key<EnumRule<DIDestructionType>> CREEPER_EXPLOSIONS = GameRuleRegistry.register("creeperExplosions", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DIDestructionType.DESTROY));
	public static GameRules.Key<BooleanRule> CREEPER_EXPLOSION_DROP_DECAY = GameRuleRegistry.register("creeperExplosionDropDecay", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<EnumRule<DIDestructionTypeWithInheritage>> CHARGED_CREEPER_EXPLOSIONS = GameRuleRegistry.register("chargedCreeperExplosions", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DIDestructionTypeWithInheritage.INHERIT_FROM_PARENT));
	public static GameRules.Key<EnumRule<InheritageTristate>> CHARGED_CREEPER_EXPLOSION_DROP_DECAY = GameRuleRegistry.register("chargedCreeperExplosionDropDecay", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(InheritageTristate.INHERIT_FROM_PARENT));
	public static GameRules.Key<EnumRule<DIDestructionType>> GHAST_FIREBALL_EXPLOSIONS = GameRuleRegistry.register("ghastFireballExplosions", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DIDestructionType.DESTROY));
	public static GameRules.Key<BooleanRule> GHAST_FIREBALL_EXPLOSION_DROP_DECAY = GameRuleRegistry.register("ghastFireballExplosionDropDecay", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> GHAST_FIREBALL_EXPLOSION_FIRE_SPREAD = GameRuleRegistry.register("ghastFireballExplosionFireSpread", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<EnumRule<DIDestructionType>> WITHER_SPAWN_EXPLOSIONS = GameRuleRegistry.register("witherSpawnExplosions", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DIDestructionType.DESTROY));
	public static GameRules.Key<BooleanRule> WITHER_SPAWN_EXPLOSION_DROP_DECAY = GameRuleRegistry.register("witherSpawnExplosionDropDecay", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createBooleanRule(false));
	public static GameRules.Key<EnumRule<DIDestructionType>> WITHER_SKULL_EXPLOSIONS = GameRuleRegistry.register("witherSkullExplosions", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createEnumRule(DIDestructionType.DESTROY));
	public static GameRules.Key<BooleanRule> WITHER_SKULL_EXPLOSION_DROP_DECAY = GameRuleRegistry.register("witherSkullExplosionDropDecay", DAMAGE_INCORPORATED_EXPLOSIONS_CATEGORY, GameRuleFactory.createBooleanRule(false));

	// General
	public static GameRules.Key<EnumRule<AllowedEntities>> FARMLAND_TRAMPLING = GameRuleRegistry.register("farmlandTrampling", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
	public static GameRules.Key<BooleanRule> CAN_WITHER_ROSE_BE_PLANTED = GameRuleRegistry.register("canWitherRoseBePlanted", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<EnumRule<AllowedEntities>> TURTLE_EGG_TRAMPLING = GameRuleRegistry.register("turtleEggTrampling", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
	public static GameRules.Key<BooleanRule> CAN_MOBS_PICK_UP_LOOT = GameRuleRegistry.register("canMobsPickUpLoot", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<EnumRule<AllowedEntities>> CAN_BURNING_MOBS_BREAK_POWDER_SNOW = GameRuleRegistry.register("canBurningMobsBreakPowderSnow", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));
	public static GameRules.Key<EnumRule<AllowedEntities>> CAN_BURNING_PROJECTILES_MODIFY_BLOCKS = GameRuleRegistry.register("canBurningProjectilesModifyBlocks", DAMAGE_INCORPORATED_GENERAL_CATEGORY, GameRuleFactory.createEnumRule(AllowedEntities.ALL));

	// Hostile Mobs
	public static GameRules.Key<BooleanRule> CAN_ENDERMEN_PICK_BLOCKS = GameRuleRegistry.register("canEndermenPickBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_ENDERMEN_PLACE_BLOCKS = GameRuleRegistry.register("canEndermenPlaceBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_SILVERFISH_INFEST_BLOCKS = GameRuleRegistry.register("canSilverfishInfestBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> PERMANENT_INFESTED_BLOCK_DAMAGE = GameRuleRegistry.register("permanentInfestedBlockDamage", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_WITHER_BREAK_BLOCKS = GameRuleRegistry.register("canWitherBreakBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_RAVAGERS_BREAK_LEAVES = GameRuleRegistry.register("canRavagersBreakLeaves", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_RAVAGERS_BREAK_CROPS = GameRuleRegistry.register("canRavagersBreakCrops", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_ENDER_DRAGON_DESTROY_BLOCKS = GameRuleRegistry.register("canEnderDragonDestroyBlocks", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> BLAZE_FIREBALL_FIRE_SPREAD = GameRuleRegistry.register("blazeFireballFireSpread", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_PIGLINS_PICK_UP_ITEMS = GameRuleRegistry.register("canPiglinsPickUpItems", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_EVOKERS_WOLOLO = GameRuleRegistry.register("canEvokersWololo", DAMAGE_INCORPORATED_HOSTILE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));

	// Undead Mobs
	public static GameRules.Key<BooleanRule> CAN_MOBS_BREAK_DOORS = GameRuleRegistry.register("canMobsBreakDoors", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIES = GameRuleRegistry.register("canTurtleEggsBeStompedByZombies", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIFIED_PIGLINS = GameRuleRegistry.register("canTurtleEggsBeStompedByZombifiedPiglins", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_ZOMBIE_VILLAGERS = GameRuleRegistry.register("canTurtleEggsBeStompedByZombieVillagers", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_HUSKS = GameRuleRegistry.register("canTurtleEggsBeStompedByHusks", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_TURTLE_EGGS_BE_STOMPED_BY_DROWNEDS = GameRuleRegistry.register("canTurtleEggsBeStompedByDrowneds", DAMAGE_INCORPORATED_UNDEAD_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));

	// Animals & Utility
	public static GameRules.Key<BooleanRule> CAN_ALLAYS_PICK_UP_ITEMS = GameRuleRegistry.register("canAllaysPickUpItems", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_SHEEP_BREAK_GRASS = GameRuleRegistry.register("canSheepBreakGrass", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_SHEEP_TURN_GRASS_BLOCKS_INTO_DIRT = GameRuleRegistry.register("canSheepTurnGrassBlocksIntoDirt", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_RABBITS_EAT_CARROT_CROPS = GameRuleRegistry.register("canRabbitsEatCarrotCrops", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_FOXES_PICK_BERRIES = GameRuleRegistry.register("canFoxesPickBerries", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> CAN_FARMER_VILLAGERS_FARM = GameRuleRegistry.register("canFarmerVillagersFarm", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));
	public static GameRules.Key<BooleanRule> SNOW_GOLEM_TRAIL = GameRuleRegistry.register("snowGolemTrail", DAMAGE_INCORPORATED_PASSIVE_MOBS_CATEGORY, GameRuleFactory.createBooleanRule(true));

	// A little trick to initialize all those game rules
	public DIGameRules() {}
}
