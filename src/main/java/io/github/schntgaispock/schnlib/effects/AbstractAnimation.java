package io.github.schntgaispock.schnlib.effects;

import java.util.Optional;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.schntgaispock.schnlib.SchnLib;
import io.github.schntgaispock.schnlib.effects.runnables.AnimationRunnable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public abstract class AbstractAnimation<T extends AnimationRunnable> {
    
    private final Optional<Integer> duration;
    private final int delay;
    private final int period;
    private final boolean isAsync;

    public abstract T getRunnable(Entity animationSource, Location startLocation);

    public void init(Entity animationSource, Location startLocation) {
        final BukkitRunnable animationRunnable = getRunnable(animationSource, startLocation);

        if (isAsync) {
            animationRunnable.runTaskTimerAsynchronously(SchnLib.getAddon(), delay, period);
        } else {
            animationRunnable.runTaskTimer(SchnLib.getAddon(), delay, period);
        }
    }

    /**
     * Called every tick of the task
     * 
     * @param source      The entity that started the animation
     * @param location    The location the animation is at
     * @param currentTick How many times the task has been ticked before
     * @return Whether or not to cancel the tick
     */
    public abstract boolean tick(T runnable);

}
