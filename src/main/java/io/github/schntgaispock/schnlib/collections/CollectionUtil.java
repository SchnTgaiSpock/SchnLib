package io.github.schntgaispock.schnlib.collections;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;
import java.util.function.Function;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.experimental.UtilityClass;

/**
 * A list of utility functions for all sorts of collections
 */
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

}
