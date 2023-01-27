package io.github.ennuil.damage_incorporated.hooks;

import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;

public interface WorldExtensions {
	Explosion createExplosion(
		@Nullable Entity entity,
		@Nullable DamageSource source,
		@Nullable ExplosionBehavior behaviour,
		double x,
		double y,
		double z,
		float power,
		boolean createFire,
		World.ExplosionSourceType sourceType,
		Explosion.DestructionType destructionType
	);

	Explosion createExplosion(
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
	);
}
