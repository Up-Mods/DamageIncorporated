package io.github.ennuil.damage_incorporated.game_rules;

import net.minecraft.world.explosion.Explosion;

public class DIEnums {
	public enum DIDestructionType {
		NONE,
		DAMAGE_ONLY,
		DESTROY,
	}

	public enum DIDestructionTypeWithInheritage {
		NONE,
		DAMAGE_ONLY,
		DESTROY,
		INHERIT_FROM_PARENT,
	}

	public enum InheritageTristate {
		FALSE,
		TRUE,
		INHERIT_FROM_PARENT,
	}

	public enum AllowedEntities {
		ALL,
		PLAYER_ONLY,
		MOB_ONLY,
		OFF
	}

	public enum OldDIDestructionType {
		NONE,
		DAMAGE_ONLY,
		LOSSLESS_EXPLOSION,
		LOSSY_EXPLOSION
	}

	public static DIEnums.DIDestructionType convertOldToNewDestructionType(String oldDestructionTypeString) {
		var oldDestructionType = Enum.valueOf(OldDIDestructionType.class, oldDestructionTypeString);

		return switch (oldDestructionType) {
			case NONE -> DIDestructionType.NONE;
			case DAMAGE_ONLY -> DIDestructionType.DAMAGE_ONLY;
			case LOSSLESS_EXPLOSION -> DIDestructionType.DESTROY;
			case LOSSY_EXPLOSION -> DIDestructionType.DESTROY;
		};
	}

	public static boolean convertOldToNewDropDecay(String oldDestructionTypeString) {
		var oldDestructionType = Enum.valueOf(OldDIDestructionType.class, oldDestructionTypeString);

		return switch (oldDestructionType) {
			case NONE -> true;
			case DAMAGE_ONLY -> true;
			case LOSSLESS_EXPLOSION -> false;
			case LOSSY_EXPLOSION -> true;
		};
	}

	public static DIEnums.DIDestructionTypeWithInheritage convertOldToNewInheritageDestructionType(String oldDestructionTypeString) {
		var oldDestructionType = Enum.valueOf(OldDIDestructionType.class, oldDestructionTypeString);

		return switch (oldDestructionType) {
			case NONE -> DIDestructionTypeWithInheritage.NONE;
			case DAMAGE_ONLY -> DIDestructionTypeWithInheritage.DAMAGE_ONLY;
			case LOSSLESS_EXPLOSION -> DIDestructionTypeWithInheritage.DESTROY;
			case LOSSY_EXPLOSION -> DIDestructionTypeWithInheritage.DESTROY;
		};
	}

	public static DIEnums.InheritageTristate convertOldToNewInheritageDropDecay(String oldDestructionTypeString) {
		var oldDestructionType = Enum.valueOf(OldDIDestructionType.class, oldDestructionTypeString);

		return switch (oldDestructionType) {
			case NONE -> InheritageTristate.TRUE;
			case DAMAGE_ONLY -> InheritageTristate.TRUE;
			case LOSSLESS_EXPLOSION -> InheritageTristate.FALSE;
			case LOSSY_EXPLOSION -> InheritageTristate.TRUE;
		};
	}
}
