package io.github.schntgaispock.schnlib.effects.handlers;

import org.bukkit.Location;

@FunctionalInterface
public interface ProjectileLandHandler {

    /**
     * Called when the projectile hits a solid block
     * @param location The location of the solid block
     * @return
     */
    public boolean onLand(Location location);

}
