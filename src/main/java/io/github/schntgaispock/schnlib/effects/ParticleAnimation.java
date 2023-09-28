package io.github.schntgaispock.schnlib.effects;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.schntgaispock.schnlib.SchnLib;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class ParticleAnimation {

    private final int duration;
    private final int delay;
    private final int period;
    private final boolean isAsync;

    public ParticleAnimation(int duration, int period, boolean isAsync) {
        this(duration, 0, period, isAsync);
    }

    public ParticleAnimation(int duration, int period) {
        this(duration, period, false);
    }

    public ParticleAnimation(int duration) {
        this(duration, 1);
    }

    public ParticleAnimation() {
        this(1);
    }


    public void init(Entity animationSource, Location startLocation) {

        BukkitRunnable animationRunnable = new BukkitRunnable() {

            private final Entity source = animationSource;
            private final Location location = startLocation;
            private final int totalTicks = duration;
            private int ticks;
            
            @Override
            public void run() {
                if (ticks > totalTicks || tick(source, location, ticks++)) {
                    cancel();
                }
            }

        };
        if (isAsync) {
            animationRunnable.runTaskTimerAsynchronously(SchnLib.getAddon(), delay, period);
        } else {
            animationRunnable.runTaskTimer(SchnLib.getAddon(), delay, period);
        }
    }

    /**
     * Called every tick of the task
     * @param source The entity that started the animation
     * @param location The location the animation is at
     * @param currentTick How many times the task has been ticked before
     * @return Whether or not to cancel the tick
     */
    public abstract boolean tick(Entity source, Location location, int currentTick);

}
