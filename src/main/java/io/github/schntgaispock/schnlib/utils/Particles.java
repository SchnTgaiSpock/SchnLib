package io.github.schntgaispock.schnlib.utils;

import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Consumer;

import org.bukkit.Location;
import org.bukkit.Particle;
import org.bukkit.util.Vector;

import io.github.schntgaispock.schnlib.collections.Pair;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class Particles {

    @Getter
    @RequiredArgsConstructor
    public static class ParticleOptions<T> {

        private final int count;
        private final double offsetX;
        private final double offsetY;
        private final double offsetZ;
        private final int extra;
        private final T data;
        private final boolean force;

        public static final ParticleOptions<?> DEFAULT = new ParticleOptions<>(1, 0, 0, 0, 0, null, true);

        public static ParticleOptions<?> count(int count) {
            return new ParticleOptions<>(count, 0, 0, 0, 0, null, true);
        }

        public static ParticleOptions<?> offset(double x, double y, double z) {
            return new ParticleOptions<>(0, x, y, z, 0, null, true);
        }

        public static ParticleOptions<?> offset(int count, double x, double y, double z) {
            return new ParticleOptions<>(count, x, y, z, 0, null, true);
        }

        public static ParticleOptions<?> offset(double x, double y, double z, int extra) {
            return new ParticleOptions<>(0, x, y, z, extra, null, true);
        }

        public static ParticleOptions<?> offset(int count, double x, double y, double z, int extra) {
            return new ParticleOptions<>(count, x, y, z, extra, null, true);
        }

    }

    public static void randomOffset(int count, double offsetX, double offsetY, double offsetZ, Location center,
            Consumer<Location> drawer) {
        final ThreadLocalRandom random = ThreadLocalRandom.current();
        for (int i = 0; i < count; i++) {
            drawer.accept(center.clone().add(
                    (random.nextDouble() * 2 - 1) * offsetX,
                    (random.nextDouble() * 2 - 1) * offsetY,
                    (random.nextDouble() * 2 - 1) * offsetZ));
        }
    }

    public static void draw(Location location, Particle particle, ParticleOptions<?> options) {
        location.getWorld().spawnParticle(
                particle,
                location.getX(),
                location.getY(),
                location.getZ(),
                options.getCount(),
                options.getOffsetX(),
                options.getOffsetY(),
                options.getOffsetZ(),
                options.getExtra(),
                options.getData(),
                options.isForce());
    }

    public static void draw(Location location, Particle particle) {
        draw(location, particle, ParticleOptions.DEFAULT);
    }

    public static void drawCircle(Location location, Particle particle, int radius, int points, Vector normal,
            ParticleOptions<?> options) {
        final Pair<Vector, Vector> orthonormalPlane = VectorUtil.getOrthonormalPlane(normal);
        final Vector v1 = orthonormalPlane.first();
        final Vector v2 = orthonormalPlane.second();
        for (double i = 0; i < points; i++) {
            final double fraction = i / points;
            final double cosValue = Math.cos(2 * Math.PI * fraction);
            final double sinValue = Math.sin(2 * Math.PI * fraction);
            draw(location.clone().add(
                    radius * (v1.getX() * cosValue + v2.getX() * sinValue),
                    radius * (v1.getY() * cosValue + v2.getY() * sinValue),
                    radius * (v1.getZ() * cosValue + v2.getZ() * sinValue)), particle, options);
        }
    }

    public static void drawCircle(Location location, Particle particle, int radius, Vector normal,
            ParticleOptions<?> options) {
        drawCircle(location, particle, radius, radius * 10, normal, options);
    }

    public static void drawCircle(Location location, Particle particle, int radius, Vector normal) {
        drawCircle(location, particle, radius, normal, ParticleOptions.DEFAULT);
    }

    public static void drawCircle(Location location, Particle particle, int radius, int points,
            ParticleOptions<?> options) {
        for (double i = 0; i < points; i++) {
            final double fraction = i / points;
            draw(location.clone().add(radius * Math.cos(2 * Math.PI * fraction), 0,
                    radius * Math.sin(2 * Math.PI * fraction)), particle, options);
        }
    }

    public static void drawCircle(Location location, Particle particle, int radius, ParticleOptions<?> options) {
        drawCircle(location, particle, radius, radius * 10, options);
    }

    public static void drawCircle(Location location, Particle particle, int radius) {
        drawCircle(location, particle, radius, ParticleOptions.DEFAULT);
    }

    // public static void drawRect(Location corner1, Location corner2, Particle
    // particle, ParticleOptions<?> options) {
    // final double x1 = corner1.getX();
    // final double x2 = corner2.getX();
    // final double y1 = corner1.getY();
    // final double y2 = corner2.getY();
    // for (double i = y1; i < y2; i += Double.compare(y1, y2) / 10d) {
    // draw(corner1.clone().add(0, 0, 0), particle);
    // }
    // // TODO
    // }

}
