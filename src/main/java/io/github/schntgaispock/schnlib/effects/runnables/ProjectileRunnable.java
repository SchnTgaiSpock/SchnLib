package io.github.schntgaispock.schnlib.effects.runnables;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import lombok.Getter;

@Getter
public abstract class ProjectileRunnable extends AnimationRunnable {

    private final Vector velocity;
    private int hits;

    public ProjectileRunnable(Entity source, Location location, Optional<Integer> totalTicks, Vector velocity) {
        super(source, location, totalTicks);
        
        this.velocity = velocity;
        this.hits = 0;
    }

    public void addHits(int hits) {
        this.hits += hits;
    }

}