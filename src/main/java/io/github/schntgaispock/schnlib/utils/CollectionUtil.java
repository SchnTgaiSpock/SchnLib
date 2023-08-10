package io.github.schntgaispock.schnlib.utils;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import com.google.common.base.Predicate;

import io.github.schntgaispock.schnlib.collections.IntPair;
import io.github.schntgaispock.schnlib.collections.Pair;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CollectionUtil {

    /**
     * Stringifies and comma-joins a list of objects
     * 
     * @param list The list of items to join
     * @return The comma-joined list
     */
    @Nonnull
    public static String commaJoin(@Nonnull Object[] list) {
        StringBuilder text = new StringBuilder();
        for (int i = 0; i < list.length - 1; i++) {
            text.append(list[i]).append(", ");
        }
        text.append(list[list.length - 1]);
        return text.toString();
    }

    /**
     * Converts a collection of items into a map, using keys generated by
     * <code>keyAccessor</code>
     * 
     * @param <K>         The type of the key
     * @param <V>         The type of the value
     * @param values      The values to convert
     * @param keyAccessor A function returning the key given a value
     * @return The new map of items
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public static <K, V> Map<K, V> toMap(Collection<V> values, Function<V, K> keyAccessor) {
        final Map<K, V> map = new HashMap<K, V>();
        addToMap(map, values, keyAccessor);
        return map;
    }

    /**
     * Puts a collection of items in a map, using keys generated by
     * <code>keyAccessor</code>
     * 
     * @param <K>         The type of the key
     * @param <V>         The type of the value
     * @param values      The values to convert
     * @param keyAccessor A function returning the key given a value
     */
    @ParametersAreNonnullByDefault
    public static <K, V> void addToMap(Map<K, V> map, Collection<V> values, Function<V, K> keyAccessor) {
        for (V value : values) {
            map.put(keyAccessor.apply(value), value);
        }
    }

    @Nonnull
    public static <K, V> HashMap<K, V> shallowCopyHashMap(@Nonnull Map<K, V> map) {
        final HashMap<K, V> newMap = new HashMap<>();
        newMap.putAll(map);
        return newMap;
    }

    /**
     * Checks if an array only contains null values
     * 
     * @param array The array to check
     * @return <code>true</code> if the array has no elements or only contains null
     *         values
     */
    public static boolean isEmpty(@Nonnull Object[] array) {
        for (final Object object : array) {
            if (object != null) {
                return false;
            }
        }

        return true;
    }

    /**
     * Picks a random item from the given list.
     * 
     * @param <T> The type of item
     * @param a   The options to choose from
     * @return A random choice from the available options
     */
    @SafeVarargs
    public static <T> T choice(@Nonnull T... a) {
        return a[ThreadLocalRandom.current().nextInt(a.length)];
    }

    /**
     * Picks a random item from the given list.
     * 
     * @param <T> The type of item
     * @param a   The options to choose from
     * @return A random choice from the available options
     */
    public static <T> T choice(@Nonnull List<T> a) {
        return a.get(ThreadLocalRandom.current().nextInt(a.size()));
    }
    
    /**
     * Strips a 2d (flattened) list of null values on all 4 sides
     * @param <T> The type of the list values
     * @param grid The flattened list to strip
     * @param width The width of the list
     * @param height The height of the list
     * @param isNullish If returns true, the element is considered null
     * @return A new stripped flat list along with its width and height
     */
    @SuppressWarnings("unchecked")
    public static <T> Pair<T[], IntPair> strip(T[] grid, int width, int height, Predicate<T> isNullish) {
        if (grid.length != width * height) {
            return Pair.of(grid, IntPair.of(width, height));
        }

        int startX = 0;
        int startY = 0;

        // Loop through each column, startX will be the first column to be non-empty
        columnLoop: for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (!isNullish.apply(grid[width * y + x])) {
                    break columnLoop;
                }
            }
            startX++;
        }

        // Same logic but for rows
        rowLoop: for (int y = 0; y < height; y++) {
            for (int x = 0; x < width; x++) {
                if (!isNullish.apply(grid[width * y + x])) {
                    break rowLoop;
                }
            }
            startY++;
        }

        int w = width - startX;
        int h = height - startY;

        // Loop through each column, backwards. w will be the first column to be
        // non-empty, minus startX
        reverseColumnLoop: for (int x = width - 1; x > startX; x--) {
            for (int y = 0; y < height; y++) {
                if (!isNullish.apply(grid[width * y + x])) {
                    break reverseColumnLoop;
                }
            }
            w--;
        }

        // Same logic but for rows
        reverseRowLoop: for (int y = height - 1; y > startY; y--) {
            for (int x = 0; x < width; x++) {
                if (!isNullish.apply(grid[width * y + x])) {
                    break reverseRowLoop;
                }
            }
            h--;
        }

        // Yes, jank, I know
        final T[] newGrid = (T[]) new Object[w * h];

        // Copying over
        for (int x = 0; x < w; x++) {
            for (int y = 0; y < h; y++) {
                newGrid[w * y + x] = grid[width * (startY + y) + startX + x];
            }
        }

        return Pair.of(newGrid, IntPair.of(w, h));
    }

    public static <T> Pair<T[], IntPair> strip(T[] grid, int width, int height) {
        return strip(grid, width, height, val -> val == null);
    }

    private static String stringify(@Nullable Object obj) {
        return obj == null ? "null" : obj.toString();
    }

    public static String stringify(Object[] flatArray, int width, int height) {
        if (flatArray.length != width * height || height <= 1) {
            return "[" + Arrays.toString(flatArray) + "]";
        }

        final StringBuffer buffer = new StringBuffer("[");
        for (int y = 0; y < height; y++) {
            buffer.append("[");
            for (int x = 0; x < width - 1; x++) {
                buffer.append(stringify(flatArray[width * y + x])).append(", ");
            }
            buffer.append(stringify(flatArray[width * (y + 1) - 1])).append(y == height - 1 ? "]]" : "]\n ");
        }
        return buffer.toString();
    }

}
