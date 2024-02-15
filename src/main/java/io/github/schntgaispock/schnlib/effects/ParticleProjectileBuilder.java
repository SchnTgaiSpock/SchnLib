package io.github.schntgaispock.schnlib.effects;

import java.util.function.BiPredicate;

import org.bukkit.Location;
import org.bukkit.entity.Entity;
import org.bukkit.util.Vector;

import io.github.schntgaispock.schnlib.effects.handlers.EntityHitHandler;
import io.github.schntgaispock.schnlib.effects.handlers.ProjectileLandHandler;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class ParticleProjectileBuilder extends AbstractAnimationBuilder {

    private Vector acceleration = new Vector(0, 0, 0);
    private double drag = 1;
    private int maxHits = 1;
    private double xWidth = 0.5;
    private double height = 0.5;
    private double zWidth = 0.5;
    private int animationsPerTick = 1;
    private EntityHitHandler hitHandler = (s, e, l, h) -> 1;
    private ProjectileLandHandler landHandler = l -> true;
    private BiPredicate<Location, Vector> animator = (l, v) -> false;

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

    public ParticleProjectileBuilder animator(BiPredicate<Location, Vector> animator) {
        this.animator = animator;
        return this;
    }

    public ParticleProjectile build() {
        return new ParticleProjectile(
            getDuration(),
            getPeriod(),
            isAsync(),
            acceleration,
            drag,
            maxHits,
            xWidth,
            height,
            zWidth,
            animationsPerTick
        ) {
            @Override
            public int onHit(Entity source, Entity entityHit, Location location, int hitsSoFar) {
                return hitHandler.onHit(source, entityHit, location, hitsSoFar);
            }

            @Override
            public boolean onLand(Location location) {
                return landHandler.onLand(location);
            }

            @Override
            public boolean animate(Location location, Vector direcion) {
                return animator.test(location, direcion);
            }
        };
    }
    
}
