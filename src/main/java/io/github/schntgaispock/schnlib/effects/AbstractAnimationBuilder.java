package io.github.schntgaispock.schnlib.effects;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class AbstractAnimationBuilder {

    private int duration = 1;
    private int delay = 0;
    private int period = 1;
    private boolean isAsync = false;

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

    public AbstractAnimationBuilder isAsync(boolean isAsync) {
        this.isAsync = isAsync;
        return this;
    }
    
}
