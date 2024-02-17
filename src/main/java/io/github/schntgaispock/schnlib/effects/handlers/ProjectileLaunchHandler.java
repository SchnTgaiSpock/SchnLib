package io.github.schntgaispock.schnlib.effects.handlers;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

@FunctionalInterface
public interface ProjectileLaunchHandler {
    
    public void onLaunch(Entity source, Location startLocation, Vector velocity);

}
