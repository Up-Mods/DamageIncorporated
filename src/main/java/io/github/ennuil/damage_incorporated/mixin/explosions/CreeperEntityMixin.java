package io.github.ennuil.damage_incorporated.mixin.explosions;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIEnums;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import io.github.ennuil.damage_incorporated.hooks.WorldExtensions;
import io.github.ennuil.damage_incorporated.utils.DIHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(CreeperEntity.class)
public abstract class CreeperEntityMixin {
	@WrapOperation(
		method = "explode",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"
		)
	)
	private Explosion di$modifyCreeperExplosion(World world, Entity entity, double x, double y, double z, float power, World.ExplosionSourceType sourceType, Operation<Explosion> original) {
		var gameRules = world.getGameRules();
		if (gameRules.getBoolean(GameRules.DO_MOB_GRIEFING)) {
			// Creepers and charged creepers are treated as separate here
			var explosions = gameRules.get(DIGameRules.CREEPER_EXPLOSIONS).get();
			boolean dropDecay = gameRules.getBoolean(DIGameRules.CREEPER_EXPLOSION_DROP_DECAY);

			if (this.isEnergySwirlActive()) {
				var chargedExplosions = gameRules.get(DIGameRules.CHARGED_CREEPER_EXPLOSIONS).get();
				var chargedDropDecay = gameRules.get(DIGameRules.CHARGED_CREEPER_EXPLOSION_DROP_DECAY).get();

				boolean inheritedExplosionDiffers = chargedExplosions == DIEnums.DIDestructionTypeWithInheritage.INHERIT_FROM_PARENT
						&& explosions != DIEnums.DIDestructionType.DESTROY;
				boolean inheritedDropDecayDiffers = chargedDropDecay == DIEnums.InheritageTristate.INHERIT_FROM_PARENT && !dropDecay;
				boolean explosionDiffers = chargedExplosions != DIEnums.DIDestructionTypeWithInheritage.INHERIT_FROM_PARENT;
				boolean dropDecayDiffers = chargedDropDecay != DIEnums.InheritageTristate.INHERIT_FROM_PARENT;

				if (inheritedExplosionDiffers || inheritedDropDecayDiffers || explosionDiffers || dropDecayDiffers) {
					var destructionType = DIHelper.getDestructionType(chargedExplosions, chargedDropDecay, explosions, dropDecay);
					power = chargedExplosions == DIEnums.DIDestructionTypeWithInheritage.INHERIT_FROM_PARENT
						? explosions == DIEnums.DIDestructionType.NONE ? 0.0F : power
						: chargedExplosions == DIEnums.DIDestructionTypeWithInheritage.NONE ? 0.0F : power;

					return ((WorldExtensions) world).createExplosion(entity, null, null, x, y, z, power, false, sourceType, destructionType);
				}
			} else {
				if (explosions != DIEnums.DIDestructionType.DESTROY || !dropDecay) {
					var destructionType = DIHelper.getDestructionType(explosions, dropDecay);
					power = explosions == DIEnums.DIDestructionType.NONE ? 0.0F : power;

					return ((WorldExtensions) world).createExplosion(entity, null, null, x, y, z, power, false, sourceType, destructionType);
				}
			}
		}

		return original.call(world, entity, x, y, z, power, sourceType);
	}

	@Shadow
	public abstract boolean isEnergySwirlActive();
}
