package io.github.ennuil.damage_incorporated.mixin.explosions;

import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import io.github.ennuil.damage_incorporated.game_rules.DIEnums;
import io.github.ennuil.damage_incorporated.game_rules.DIGameRules;
import io.github.ennuil.damage_incorporated.hooks.WorldExtensions;
import io.github.ennuil.damage_incorporated.utils.DIHelper;
import net.minecraft.entity.Entity;
import net.minecraft.entity.projectile.FireballEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;
import net.minecraft.world.explosion.Explosion;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

@Mixin(FireballEntity.class)
public abstract class FireballEntityMixin {
	@WrapOperation(
		method = "onCollision",
		at = @At(
			value = "INVOKE",
			target = "Lnet/minecraft/world/World;createExplosion(Lnet/minecraft/entity/Entity;DDDFZLnet/minecraft/world/World$ExplosionSourceType;)Lnet/minecraft/world/explosion/Explosion;"
		)
	)
	private Explosion di$modifyGhastFireballExplosion(World world, Entity entity, double x, double y, double z, float power, boolean createFire, World.ExplosionSourceType sourceType, Operation<Explosion> original) {
		var gameRules = world.getGameRules();
		if (gameRules.getBoolean(GameRules.DO_MOB_GRIEFING)) {
			var explosions = gameRules.get(DIGameRules.GHAST_FIREBALL_EXPLOSIONS).get();
			boolean dropDecay = gameRules.getBoolean(DIGameRules.GHAST_FIREBALL_EXPLOSION_DROP_DECAY);
			boolean fireSpread = gameRules.getBoolean(DIGameRules.GHAST_FIREBALL_EXPLOSION_FIRE_SPREAD);

			if (explosions != DIEnums.DIDestructionType.DESTROY || !dropDecay) {
				var destructionType = DIHelper.getDestructionType(explosions, dropDecay);
				return ((WorldExtensions) world).createExplosion(entity, null, null, x, y, z, power, fireSpread, sourceType, destructionType);
			} else if (!fireSpread) {
				return original.call(world, entity, x, y, z, power, fireSpread, sourceType);
			}
		}

		return original.call(world, entity, x, y, z, power, createFire, sourceType);
	}
}
