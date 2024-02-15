package io.github.schntgaispock.schnlib.effects;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import io.github.schntgaispock.schnlib.effects.runnables.ProjectileRunnable;
import lombok.Getter;

@Getter
public abstract class ParticleProjectile extends AbstractAnimation<ProjectileRunnable> {

    private final BoundingBox hitbox;
    private final double[] searchBox;
    private final int maxHits;
    private final Vector acceleration;
    private final double drag;
    private final int animationsPerTick;

    public ParticleProjectile(int duration, int period, boolean isAsync, 
            Vector acceleration, double drag, int maxHits, BoundingBox hitbox, int animationsPerTick) {
        super(duration, 0, period, isAsync);

        this.acceleration = acceleration;
        this.drag = drag;
        this.maxHits = maxHits;
        this.hitbox = hitbox;
        this.searchBox = new double[] { hitbox.getWidthX(), hitbox.getHeight(), hitbox.getWidthZ() };
        this.animationsPerTick = animationsPerTick;
    }

    /**
     * Called when this projectile hits an entity
     * @param source The entity that shot the projectile
     * @param entityHit The entity hit
     * @param location The location of the hit
     * @param hitsSoFar The number of enemies hit so far
     * @return The number of hits to increment
     */
    public int onHit(Entity source, Entity entityHit, Location location, int hitsSoFar) {
        return 1;
    };

    /**
     * Called when the projectile hits a solid block
     * @param location The location of the solid block
     * @return
     */
    public boolean onLand(Location location) {
        return true;
    };

    public abstract boolean animate(Location location, Vector direcion);

    @Override
    public ProjectileRunnable getRunnable(Entity animationSource, Location startLocation) {
        return new ProjectileRunnable(animationSource, startLocation, getDuration(), animationSource.getLocation().getDirection()) {
            @Override
            public boolean tick() {
                return ParticleProjectile.this.tick(this);
            }
        };
    }

    @Override
    public boolean tick(ProjectileRunnable runnable) {
        final Location location = runnable.getLocation();
        final Entity source = runnable.getSource();
        final Vector direction = runnable.getDirection();

        for (int i = 0; i < animationsPerTick; i++) {
            animate(location, direction);
            direction.add(acceleration);
            direction.multiply(drag);
            location.add(direction);
    
            if (location.getBlock().getType().isSolid() && onLand(location)) {
                return true;
            }
    
            final Collection<Entity> nearbyEntities = location
                .getWorld()
                .getNearbyEntities(
                    location,
                    searchBox[0]/2, searchBox[1]/2, searchBox[2]/2,
                    entity -> hitbox.overlaps(entity.getBoundingBox())
                );
    
            nearbyEntities.remove(source);
            for (final Entity entity : nearbyEntities) {
                runnable.addHits(onHit(source, entity, location, runnable.getHits()));
                if (runnable.getHits() > maxHits) {
                    return true;
                }
            }
        }

        return false;
    }
    
}
