package io.github.schntgaispock.schnlib.utils;

import org.bukkit.util.Vector;

import io.github.schntgaispock.schnlib.collections.Pair;
import lombok.experimental.UtilityClass;

@UtilityClass
public class VectorUtil {

    public static Pair<Vector, Vector> getOrthonormalPlane(Vector normal) {
        if (normal.lengthSquared() == 0) {
            return Pair.of(normal, normal);
        }
        Vector v1;
        if (normal.getZ() != 0) {
            v1 = new Vector(1, 1, -(normal.getX() + normal.getY()) / normal.getZ());
        } else if (normal.getY() != 0) {
            v1 = new Vector(1, -(normal.getX() + normal.getZ()) / normal.getY(), 1);
        } else {
            v1 = new Vector(-(normal.getY() + normal.getZ()) / normal.getX(), 1, 1);
        }

        final Vector v2 = v1.getCrossProduct(normal);
        v1.normalize();
        v2.normalize();

        return Pair.of(v1, v2);
    }

}
