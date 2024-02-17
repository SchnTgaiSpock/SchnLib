package io.github.schntgaispock.schnlib.effects;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public abstract class AbstractAnimationBuilder {

    protected int duration = 1;
    protected int delay = 0;
    protected int period = 1;
    protected boolean async = false;
    protected boolean unlimited = false;

    public AbstractAnimationBuilder duration(int duration) {
        this.duration = duration;
        return this;
    }

    public AbstractAnimationBuilder delay(int delay) {
        this.delay = delay;
        return this;
    }

    public AbstractAnimationBuilder period(int period) {
        this.period = period;
        return this;
    }

    public AbstractAnimationBuilder async(boolean isAsync) {
        this.async = isAsync;
        return this;
    }

    public AbstractAnimationBuilder unlimited(boolean isUnlimited) {
        this.unlimited = isUnlimited;
        return this;
    }
    
}
