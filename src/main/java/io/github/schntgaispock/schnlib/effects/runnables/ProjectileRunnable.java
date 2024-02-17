package io.github.schntgaispock.schnlib.effects.runnables;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import lombok.Getter;

@Getter
public abstract class ProjectileRunnable<ProjectileData> extends AnimationRunnable {

    private final Vector velocity;
    private int hits;
    private final ProjectileData data;

    public ProjectileRunnable(Entity source, Location location, Optional<Integer> totalTicks, Vector velocity, ProjectileData data) {
        super(source, location, totalTicks);
        
        this.velocity = velocity;
        this.hits = 0;
        this.data = data;
    }

    public void addHits(int hits) {
        this.hits += hits;
    }

}