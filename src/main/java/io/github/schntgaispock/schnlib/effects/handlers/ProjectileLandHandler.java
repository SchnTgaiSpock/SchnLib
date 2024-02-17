package io.github.schntgaispock.schnlib.effects.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

@FunctionalInterface
public interface ProjectileLandHandler {

    /**
     * Called when the projectile hits a solid block
     * @param location The location of the solid block
     * @return
     */
    public boolean onLand(Entity source, Location location, Vector velocity);

}
