package io.github.schntgaispock.schnlib.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Stream;

import javax.annotation.Nonnull;

public class Counter<T> implements Map<T, Integer> {

    private Map<T, Integer> map = new HashMap<>();

    public Counter() {}

    public Counter(Set<T> set) {
        for (T entry : set) {
            map.put(entry, 1);
        }
    }

    public Counter(Map<T, Integer> map) {
        for (Map.Entry<T, Integer> entry : map.entrySet()) {
            this.map.put(entry.getKey(), entry.getValue().intValue());
        }
    }

    @Override
    public Integer put(T item, Integer amount) {
        return map.put(item, map.getOrDefault(item, 0) + amount);
    }

    public void add(T item) {
        put(item, 1);
    }

    @Override
    @Nonnull
    public String toString() {
        return map.toString();
    }

    @Nonnull
    public Stream<Map.Entry<T, Integer>> stream() {
        return map.entrySet().stream();
    }

    @Override
    public int size() {
        return map.size();
    }

    @Override
    public boolean isEmpty() {
        return map.isEmpty();
    }

    @Override
    public boolean containsKey(Object key) {
        return map.containsKey(key);
    }

    @Override
    public boolean containsValue(Object value) {
        return map.containsValue(value);
    }

    @Override
    public Integer remove(Object key) {
        return map.remove(key);
    }

    @Override
    public void putAll(Map<? extends T, ? extends Integer> m) {
        for (Map.Entry<? extends T, ? extends Integer> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Set<T> keySet() {
        return map.keySet();
    }

    @Override
    public Collection<Integer> values() {
        return map.values();
    }

    @Override
    public Set<Entry<T, Integer>> entrySet() {
        return map.entrySet();
    }

    @Override
    public Integer get(Object key) {
        return map.get(key);
    }

    @Override
    public void clear() {
        map.clear();
    }

    /**
     * @return The entries with the highest value
     */
    public List<Map.Entry<T, Integer>> maxima() {
        final List<Map.Entry<T, Integer>> maxima = new ArrayList<>();
        int maxValue = Integer.MIN_VALUE;
        for (final Entry<T, Integer> entry : entrySet()) {
            if (entry.getValue() > maxValue) {
                maxima.clear();
                maxValue = entry.getValue();
                maxima.add(entry);
            } else if (entry.getValue() == maxValue) {
                maxima.add(entry);
            }
        }
        return maxima;
    }

    /**
     * @return The entries with the highest value
     */
    public List<Map.Entry<T, Integer>> minima() {
        final List<Map.Entry<T, Integer>> maxima = new ArrayList<>();
        int minValue = Integer.MAX_VALUE;
        for (final Entry<T, Integer> entry : entrySet()) {
            if (entry.getValue() < minValue) {
                maxima.clear();
                minValue = entry.getValue();
                maxima.add(entry);
            } else if (entry.getValue() == minValue) {
                maxima.add(entry);
            }
        }
        return maxima;
    }

    public int total() {
        return stream().reduce(0, (sum, entry) -> entry.getValue() + sum, (s1, s2) -> s1 + s2);
    }

}
