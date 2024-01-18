package io.github.schntgaispock.schnlib.effects.runnables;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import lombok.Getter;

@Getter
public abstract class DirectionalRunnable extends AnimationRunnable {

    private final Vector direction;

    public DirectionalRunnable(Entity source, Location location, int totalTicks, Vector direction) {
        super(source, location, totalTicks);
        
        this.direction = direction;
    }

}