package io.github.ennuil.damage_incorporated.mixin.explosions.world;

import io.github.ennuil.damage_incorporated.hooks.WorldExtensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(World.class)
public abstract class WorldMixin implements WorldExtensions {
	@Override
	public Explosion createExplosion(
		@Nullable Entity entity,
		@Nullable DamageSource source,
		@Nullable ExplosionBehavior behavior,
		double x,
		double y,
		double z,
		float power,
		boolean createFire,
		World.ExplosionSourceType sourceType,
		Explosion.DestructionType destructionType
	) {
		return this.createExplosion(entity, source, behavior, x, y, z, power, createFire, sourceType, destructionType, true);
	}

	@Override
	public Explosion createExplosion(
		@Nullable Entity entity,
		@Nullable DamageSource source,
		@Nullable ExplosionBehavior behaviour,
		double x,
		double y,
		double z,
		float power,
		boolean createFire,
		World.ExplosionSourceType sourceType,
		Explosion.DestructionType destructionType,
		boolean particles
	) {
		// If "Destroy with Decay" is used, then we can safely override it with vanilla's drop decay
		if (destructionType == Explosion.DestructionType.DESTROY_WITH_DECAY) {
			if (sourceType == World.ExplosionSourceType.MOB) {
				this.getDestructionType(GameRules.MOB_EXPLOSION_DROP_DECAY);
			}
		}

		Explosion explosion = new Explosion((World) (Object) this, entity, source, behaviour, x, y, z, power, createFire, destructionType);
		explosion.collectBlocksAndDamageEntities();
		explosion.affectWorld(particles);
		return explosion;
	}

	@Shadow
	protected abstract Explosion.DestructionType getDestructionType(GameRules.Key<GameRules.BooleanRule> dropDecayRule);
}
