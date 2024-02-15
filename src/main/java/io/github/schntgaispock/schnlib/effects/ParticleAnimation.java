package io.github.schntgaispock.schnlib.effects;

import org.bukkit.Location;
import org.bukkit.entity.Entity;

import io.github.schntgaispock.schnlib.effects.runnables.AnimationRunnable;
import lombok.Getter;

@Getter
public abstract class ParticleAnimation extends AbstractAnimation<AnimationRunnable> {
    
    public ParticleAnimation(int duration, int delay, int period, boolean isAsync) {
        super(duration, delay, period, isAsync);
    }

    public AnimationRunnable getRunnable(Entity animationSource, Location startLocation) {
        return new AnimationRunnable(animationSource, startLocation, getDuration()) {
            @Override
            public boolean tick() {
                return ParticleAnimation.this.tick(this);
            }
        };
    }

}
