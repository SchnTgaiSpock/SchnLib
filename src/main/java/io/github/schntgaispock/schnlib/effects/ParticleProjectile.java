package io.github.schntgaispock.schnlib.effects;

import java.util.Collection;
import java.util.Optional;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import io.github.schntgaispock.schnlib.effects.handlers.EntityHitHandler;
import io.github.schntgaispock.schnlib.effects.handlers.ProjectileLandHandler;
import io.github.schntgaispock.schnlib.effects.handlers.ProjectileLaunchHandler;
import io.github.schntgaispock.schnlib.effects.runnables.ProjectileRunnable;
import lombok.Getter;

@Getter
public abstract class ParticleProjectile extends AbstractAnimation<ProjectileRunnable> implements EntityHitHandler, ProjectileLandHandler, ProjectileLaunchHandler {

    private final double initialSpeed;
    private final Vector acceleration;
    private final double drag;
    private final int maxHits;
    private final double xWidth;
    private final double height;
    private final double zWidth;
    private final int animationsPerTick;
    
    @ParametersAreNonnullByDefault
    public ParticleProjectile(Optional<Integer> duration, int period, boolean isAsync, double initialSpeed,
            Vector acceleration, double drag, int maxHits, double xWidth, double height, double zWidth, int animationsPerTick) {
        super(duration, 0, period, isAsync);

        this.initialSpeed = initialSpeed;
        this.acceleration = acceleration;
        this.drag = drag;
        this.maxHits = maxHits;
        this.xWidth = xWidth;
        this.height = height;
        this.zWidth = zWidth;
        this.animationsPerTick = animationsPerTick;
    }

    /**
     * Play an animation
     * @param location The location of the projectile
     * @param velocity The velocity of the projectile
     * @return Whether or not to destroy this projectile
     */
    public abstract boolean animate(Location location, Vector velocity);

    @Override
    public ProjectileRunnable getRunnable(Entity animationSource, Location startLocation) {
        return new ProjectileRunnable(animationSource, startLocation, getDuration(), startLocation.getDirection().multiply(initialSpeed)) {
            @Override
            public boolean tick() {
                return ParticleProjectile.this.tick(this);
            }
        };
    }

    @Override
    public void init(Entity animationSource, Location startLocation) {
        super.init(animationSource, startLocation);
        onLaunch(animationSource, startLocation, startLocation.getDirection().multiply(initialSpeed));
    }

    @Override
    public boolean tick(ProjectileRunnable runnable) {
        final Location location = runnable.getLocation();
        final Entity source = runnable.getSource();
        final Vector velocity = runnable.getVelocity();

        for (int i = 0; i < animationsPerTick; i++) {
            if (animate(location, velocity)) {
                return true;
            };

            velocity.add(acceleration);
            velocity.multiply(drag);
            location.add(velocity);
    
            if (location.getBlock().getType().isSolid() && onLand(source, location, velocity)) {
                return true;
            }
    
            final Collection<Entity> nearbyEntities = location
                .getWorld()
                .getNearbyEntities(
                    location,
                    xWidth/2, height/2, zWidth/2,
                    entity -> entity.getBoundingBox().overlaps(BoundingBox.of(location, xWidth, height, zWidth))
                );
    
            nearbyEntities.remove(source);
            for (final Entity entity : nearbyEntities) {
                runnable.addHits(onHit(source, entity, location, velocity, runnable.getHits()));
                if (runnable.getHits() >= maxHits) {
                    return true;
                }
            }
        }

        return false;
    }

    public static ParticleProjectileBuilder builder() {
        return new ParticleProjectileBuilder();
    }
    
}
