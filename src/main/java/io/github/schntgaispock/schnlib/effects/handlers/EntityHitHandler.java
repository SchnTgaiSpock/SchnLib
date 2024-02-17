package io.github.schntgaispock.schnlib.effects.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

@FunctionalInterface
public interface EntityHitHandler {
    
    /**
     * Called when this projectile hits an entity
     * @param source The entity that shot the projectile
     * @param entityHit The entity hit
     * @param location The location of the hit
     * @param hitsSoFar The number of enemies hit so far
     * @return The number of hits to increment
     */
    public int onHit(Entity source, Entity entityHit, Location location, Vector velocity, int hitsSoFar);
}
