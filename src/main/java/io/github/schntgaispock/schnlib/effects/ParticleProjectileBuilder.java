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
public class ParticleProjectileBuilder<ProjectileData> extends AbstractAnimationBuilder {

    protected double initialSpeed = 1;
    protected Vector acceleration = new Vector(0, 0, 0);
    protected double drag = 1;
    protected int maxHits = 1;
    protected double xWidth = 0.5;
    protected double height = 0.5;
    protected double zWidth = 0.5;
    protected int animationsPerTick = 1;
    protected EntityHitHandler<ProjectileData> hitHandler = (s, e, l, v, h, d) -> 1;
    protected ProjectileLandHandler<ProjectileData> landHandler = (e, l, v, d) -> true;
    protected ProjectileLaunchHandler<ProjectileData> launchHandler = (e, l, v, d) -> {};
    protected BiPredicate<Location, Vector> animator = (l, v) -> false;

    @Override
    public ParticleProjectileBuilder<ProjectileData> duration(int duration) {
        this.duration = duration;
        return this;
    }

    @Override
    public ParticleProjectileBuilder<ProjectileData> delay(int delay) {
        this.delay = delay;
        return this;
    }

    @Override
    public ParticleProjectileBuilder<ProjectileData> period(int period) {
        this.period = period;
        return this;
    }

    @Override
    public ParticleProjectileBuilder<ProjectileData> async(boolean isAsync) {
        this.async = isAsync;
        return this;
    }

    @Override
    public ParticleProjectileBuilder<ProjectileData> unlimited(boolean isUnlimited) {
        this.unlimited = isUnlimited;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> initialSpeed(double initialSpeed) {
        this.initialSpeed = initialSpeed;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> forces(Vector acceleration, double drag) {
        this.acceleration = acceleration;
        this.drag = drag;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> maxHits(int maxHits) {
        this.maxHits = maxHits;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> dimensions(double xWidth, double height, double zWidth) {
        this.xWidth = xWidth;
        this.height = height;
        this.zWidth = zWidth;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> animationsPerTick(int animationsPerTick) {
        this.animationsPerTick = animationsPerTick;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> onHit(EntityHitHandler<ProjectileData> onHit) {
        this.hitHandler = onHit;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> onLand(ProjectileLandHandler<ProjectileData> onLand) {
        this.landHandler = onLand;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> onLaunch(ProjectileLaunchHandler<ProjectileData> onLaunch) {
        this.launchHandler = onLaunch;
        return this;
    }

    public ParticleProjectileBuilder<ProjectileData> animator(BiPredicate<Location, Vector> animator) {
        this.animator = animator;
        return this;
    }

    public ParticleProjectile<ProjectileData> build() {
        return new ParticleProjectile<ProjectileData>(
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
            public int onHit(Entity source, Entity entityHit, Location location, Vector velocity, int hitsSoFar, ProjectileData data) {
                return hitHandler.onHit(source, entityHit, location, velocity, hitsSoFar, data);
            }

            @Override
            public boolean onLand(Entity source, Location location, Vector velocity, ProjectileData data) {
                return landHandler.onLand(source, location, velocity, data);
            }

            @Override
            public void onLaunch(Entity source, Location startLocation, Vector velocity, ProjectileData data) {
                launchHandler.onLaunch(source, startLocation, velocity, data);
            }

            @Override
            public boolean animate(Location location, Vector direcion) {
                return animator.test(location, direcion);
            }
        };
    }
    
}
