package io.github.schntgaispock.schnlib.effects;

import java.util.Optional;
import java.util.function.BiPredicate;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import io.github.schntgaispock.schnlib.effects.handlers.EntityHitHandler;
import io.github.schntgaispock.schnlib.effects.handlers.ProjectileLandHandler;
import io.github.schntgaispock.schnlib.effects.handlers.ProjectileLaunchHandler;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ParticleProjectileBuilder extends AbstractAnimationBuilder {

    protected double initialSpeed = 1;
    protected Vector acceleration = new Vector(0, 0, 0);
    protected double drag = 1;
    protected int maxHits = 1;
    protected double xWidth = 0.5;
    protected double height = 0.5;
    protected double zWidth = 0.5;
    protected int animationsPerTick = 1;
    protected EntityHitHandler hitHandler = (s, e, l, d, h) -> 1;
    protected ProjectileLandHandler landHandler = (e, l, d) -> true;
    protected ProjectileLaunchHandler launchHandler = (e, l, d) -> {};
    protected BiPredicate<Location, Vector> animator = (l, v) -> false;

    @Override
    public ParticleProjectileBuilder duration(int duration) {
        return (ParticleProjectileBuilder) super.duration(duration);
    }

    @Override
    public ParticleProjectileBuilder delay(int delay) {
        return (ParticleProjectileBuilder) super.delay(delay);
    }

    @Override
    public ParticleProjectileBuilder period(int period) {
        return (ParticleProjectileBuilder) super.period(period);
    }

    @Override
    public ParticleProjectileBuilder async(boolean isAsync) {
        return (ParticleProjectileBuilder) super.async(isAsync);
    }

    @Override
    public ParticleProjectileBuilder unlimited(boolean isUnlimited) {
        return (ParticleProjectileBuilder) super.unlimited(isUnlimited);
    }

    public ParticleProjectileBuilder initialSpeed(double initialSpeed) {
        this.initialSpeed = initialSpeed;
        return this;
    }

    public ParticleProjectileBuilder forces(Vector acceleration, double drag) {
        this.acceleration = acceleration;
        this.drag = drag;
        return this;
    }

    public ParticleProjectileBuilder maxHits(int maxHits) {
        this.maxHits = maxHits;
        return this;
    }

    public ParticleProjectileBuilder dimensions(double xWidth, double height, double zWidth) {
        this.xWidth = xWidth;
        this.height = height;
        this.zWidth = zWidth;
        return this;
    }

    public ParticleProjectileBuilder animationsPerTick(int animationsPerTick) {
        this.animationsPerTick = animationsPerTick;
        return this;
    }

    public ParticleProjectileBuilder onHit(EntityHitHandler onHit) {
        this.hitHandler = onHit;
        return this;
    }

    public ParticleProjectileBuilder onLand(ProjectileLandHandler onLand) {
        this.landHandler = onLand;
        return this;
    }

    public ParticleProjectileBuilder onLaunch(ProjectileLaunchHandler onLaunch) {
        this.launchHandler = onLaunch;
        return this;
    }

    public ParticleProjectileBuilder animator(BiPredicate<Location, Vector> animator) {
        this.animator = animator;
        return this;
    }

    public ParticleProjectile build() {
        return new ParticleProjectile(
            isUnlimited() ? Optional.empty() : Optional.of(getDuration()),
            getPeriod(),
            isAsync(),
            initialSpeed,
            acceleration,
            drag,
            maxHits,
            xWidth,
            height,
            zWidth,
            animationsPerTick
        ) {
            @Override
            public int onHit(Entity source, Entity entityHit, Location location, Vector velocity, int hitsSoFar) {
                return hitHandler.onHit(source, entityHit, location, velocity, hitsSoFar);
            }

            @Override
            public boolean onLand(Entity source, Location location, Vector velocity) {
                return landHandler.onLand(source, location, velocity);
            }

            @Override
            public void onLaunch(Entity source, Location startLocation, Vector velocity) {
                launchHandler.onLaunch(source, startLocation, velocity);
            }

            @Override
            public boolean animate(Location location, Vector direcion) {
                return animator.test(location, direcion);
            }
        };
    }
    
}
