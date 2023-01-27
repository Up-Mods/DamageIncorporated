package io.github.ennuil.damage_incorporated.utils;

import io.github.ennuil.damage_incorporated.game_rules.DIEnums;
import io.github.ennuil.damage_incorporated.game_rules.DIEnums.DIDestructionType;
import net.minecraft.world.explosion.Explosion;

public class DIHelper {
	public static Explosion.DestructionType getDestructionType(DIDestructionType diDestructionType, boolean dropDecay) {
		var baselineDestructionType = dropDecay ? Explosion.DestructionType.DESTROY_WITH_DECAY : Explosion.DestructionType.DESTROY;

		return switch (diDestructionType) {
			case DESTROY -> baselineDestructionType;
			case DAMAGE_ONLY, NONE -> Explosion.DestructionType.KEEP;
		};
	}

	public static Explosion.DestructionType getDestructionType(
		DIEnums.DIDestructionTypeWithInheritage diDestructionType,
		DIEnums.InheritageTristate dropDecay,
		DIEnums.DIDestructionType inheritedDiDestructionType,
		boolean inheritedDropDecay
	) {
		var baselineDestructionType = switch (dropDecay) {
			case TRUE -> Explosion.DestructionType.DESTROY_WITH_DECAY;
			case FALSE -> Explosion.DestructionType.DESTROY;
			case INHERIT_FROM_PARENT -> inheritedDropDecay ? Explosion.DestructionType.DESTROY_WITH_DECAY : Explosion.DestructionType.DESTROY;
		};

		return switch (diDestructionType) {
			case DESTROY -> baselineDestructionType;
			case DAMAGE_ONLY, NONE -> Explosion.DestructionType.KEEP;
			case INHERIT_FROM_PARENT -> switch (inheritedDiDestructionType) {
				case DESTROY -> baselineDestructionType;
				case DAMAGE_ONLY, NONE -> Explosion.DestructionType.KEEP;
			};
		};
	}
}
