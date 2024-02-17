package io.github.schntgaispock.schnlib.cooldown;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.ToString;

@ToString
public class Cooldown<K, U> {

    private final @Getter Map<K, Map<U, Long>> cooldowns = new HashMap<>();

    public Cooldown() {}

    public boolean isOnCooldown(K key, U value, long cooldown) {
        return getRemainingDuration(key, value, cooldown) > 0;
    }

    public long getRemainingDuration(K key, U value, long cooldown) {
        return cooldown - getTimeElapsed(key, value);
    }

    public long getTimeElapsed(K key, U value) {
        return System.currentTimeMillis() - getLastResetTime(key, value);
    }

    public long getLastResetTime(K key, U value) {
        if (!cooldowns.containsKey(key)) return 0;
        final Map<U, Long> valueCds = cooldowns.get(key);
        if (!valueCds.containsKey(value)) return 0;
        return valueCds.get(value);
    }

    public void resetCooldown(K key, U value) {
        if (cooldowns.containsKey(key)) {
            cooldowns.get(key).put(value, System.currentTimeMillis());
        } else {
            final Map<U, Long> map = new HashMap<>();
            map.put(value, System.currentTimeMillis());
            cooldowns.put(key, map);
        }
    }
}
