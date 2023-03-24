package io.github.ennuil.damage_incorporated.mixin.explosions.world;

import io.github.ennuil.damage_incorporated.hooks.WorldExtensions;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.network.packet.s2c.play.ExplosionOccursS2CPacket;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import net.minecraft.world.explosion.ExplosionBehavior;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

import java.util.List;

@Mixin(ServerWorld.class)
public abstract class ServerWorldMixin implements WorldExtensions {
	@Shadow
	@Final
	List<ServerPlayerEntity> players;

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
		Explosion.DestructionType destructionType
	) {
		var explosion = this.createExplosion(entity, source, behaviour, x, y, z, power, createFire, sourceType, destructionType, false);
		if (!explosion.isDestructive()) {
			explosion.clearAffectedBlocks();
		}

		for (var serverPlayerEntity : this.players) {
			if (serverPlayerEntity.squaredDistanceTo(x, y, z) < 4096.0) {
				serverPlayerEntity.networkHandler.sendPacket(
					new ExplosionOccursS2CPacket(x, y, z, power, explosion.getAffectedBlocks(), (Vec3d)explosion.getAffectedPlayers().get(serverPlayerEntity))
				);
			}
		}

		return explosion;
	}
}
