package io.github.schntgaispock.schnlib.effects.runnables;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AnimationRunnable extends BukkitRunnable {

    private final Entity source;
    private final Location location;
    private final Optional<Integer> totalTicks;
    private int ticks = 0;

    public abstract boolean tick();

    @Override
    public void run() {
        if (!isCancelled() && ((totalTicks.isPresent() && ticks >= totalTicks.get()) || tick())) {
            cancel();
        }
        ticks++;
    }
    
}