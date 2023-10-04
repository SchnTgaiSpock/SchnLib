package io.github.schntgaispock.schnlib.effects;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import io.github.schntgaispock.schnlib.effects.runnables.DirectionalRunnable;
import lombok.Getter;

public abstract class ParticleProjectile extends AbstractAnimation<DirectionalRunnable> {

    private final @Getter BoundingBox hitbox;
    private final double[] searchBox;
    private final int maxHits;
    private final Vector gravity;
    private final double drag;

    public ParticleProjectile(int duration, int period, boolean isAsync, 
            Vector gravity, double drag, int maxHits, BoundingBox hitbox) {
        super(duration, period, isAsync);

        this.gravity = gravity;
        this.drag = drag;
        this.maxHits = maxHits;
        this.hitbox = hitbox;
        this.searchBox = new double[] { hitbox.getWidthX(), hitbox.getHeight(), hitbox.getWidthZ() };
    }

    /**
     * Returns whether or not to destroy this projectile
     */
    public abstract boolean onHit(Entity source, Entity entityHit, Location location);

    /**
     * Returns whether or not to destroy this projectile
     */
    public abstract boolean onLand(Location location);

    public abstract boolean animate(Location location, Vector direcion);

    @Override
    public DirectionalRunnable getRunnable(Entity animationSource, Location startLocation) {
        return new DirectionalRunnable(animationSource, startLocation, getDuration(), animationSource.getLocation().getDirection()) {
            @Override
            public boolean tick() {
                return ParticleProjectile.this.tick(this);
            }
        };
    }

    @Override
    public boolean tick(DirectionalRunnable runnable) {
        final Location location = runnable.getLocation();
        final Entity source = runnable.getSource();
        final Vector direction = runnable.getDirection();

        animate(location, direction);
        direction.add(gravity);
        direction.multiply(drag);

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
        int hitEntities = 0;
        for (Entity entity : nearbyEntities) {
            if (++hitEntities > maxHits) {
                return true;
            }

            onHit(source, entity, location);
        }

        return false;
    }
    
}
