package io.github.schntgaispock.schnlib.effects.runnables;

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
    private final int totalTicks;
    private int ticks = 0;

    public abstract boolean tick();

    @Override
    public void run() {
        if (!isCancelled() && (ticks >= totalTicks || tick())) {
            cancel();
        }
        ticks++;
    }
}