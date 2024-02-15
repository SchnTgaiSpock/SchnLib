package io.github.schntgaispock.schnlib.effects.runnables;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import lombok.Getter;

@Getter
public abstract class ProjectileRunnable extends AnimationRunnable {

    private final Vector direction;
    private int hits;

    public ProjectileRunnable(Entity source, Location location, int totalTicks, Vector direction) {
        super(source, location, totalTicks);
        
        this.direction = direction;
        this.hits = 0;
    }

    public void addHits(int hits) {
        this.hits += hits;
    }

}