package io.github.schntgaispock.schnlib.effects;

import java.util.Collection;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.BoundingBox;
import org.bukkit.util.Vector;

import lombok.Getter;

public abstract class ParticleProjectile extends ParticleAnimation {

    private final @Getter BoundingBox[] hitboxes;
    private final double[] searchBox;
    private final int hits;
    private Vector direction;
    private final Vector gravity;
    private final double drag;

    public ParticleProjectile(int duration, int period, boolean isAsync, 
            Vector gravity, double drag, int hits, BoundingBox... hitboxes) {
        super(duration, period, isAsync);

        this.gravity = gravity;
        this.drag = drag;
        this.hits = hits;
        this.hitboxes = hitboxes;
        double searchX = 0.1;
        double searchY = 0.1;
        double searchZ = 0.1;
        for (BoundingBox hitbox : hitboxes) {
            if (hitbox.getWidthX() > searchX) {
                searchX = hitbox.getWidthX();
            }
            if (hitbox.getHeight() > searchY) {
                searchY = hitbox.getHeight();
            }
            if (hitbox.getWidthZ() > searchZ) {
                searchZ = hitbox.getWidthZ();
            }
        }
        this.searchBox = new double[] { searchX, searchY, searchZ };
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
    public boolean tick(Entity source, Location location, int currentTick) {

        animate(location, direction);
        direction.add(gravity);
        direction.multiply(drag);

        if (location.getBlock().getType().isSolid() && onLand(location)) {
            return true;
        }

        final Collection<Entity> nearbyEntities = location
            .getWorld()
            .getNearbyEntities(location, searchBox[0]/2, searchBox[1]/2, searchBox[2]/2, entity -> {
                for (BoundingBox hitbox : hitboxes) {
                    if (hitbox.overlaps(entity.getBoundingBox())) return true;
                }
                return false;
            });

        nearbyEntities.remove(source);
        int hitEntities = 0;
        for (Entity entity : nearbyEntities) {
            if (++hitEntities > hits) {
                return true;
            }

            onHit(source, entity, location);
        }

        return false;
    }
    
}
