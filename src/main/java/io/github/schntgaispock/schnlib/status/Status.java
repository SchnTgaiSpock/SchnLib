package io.github.schntgaispock.schnlib.status;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;

import io.github.schntgaispock.schnlib.collections.Pair;
import lombok.Getter;

public class Status implements Listener {

    private final @Getter int maxStacks = 1;
    /**
     * Maximum duration in seconds
     */
    private final @Getter int maxDuration = -1;
    private final Map<StatusHolder, Pair<Long, Integer>> holders = new HashMap<>();

    public void register(Plugin addon) {
        Bukkit.getPluginManager().registerEvents(this, addon);
    }

    public Map<StatusHolder, Pair<Long, Integer>> getHolders() {
        final long currentTime = System.currentTimeMillis();

        holders.entrySet().removeIf(entry -> isExpired(entry.getValue().first(), currentTime));
        return Collections.unmodifiableMap(holders);
    }

    public boolean existsOn(StatusHolder holder) {
        if (holders.containsKey(holder)) {
            return true;
        }
        holders.remove(holder);
        return false;
    }

    public boolean existsOn(Entity entity) {
        return existsOn(new EntityStatusHolder(entity));
    }

    public boolean existsOn(Block block) {
        return existsOn(new BlockStatusHolder(block));
    }

    private boolean isExpired(long applicationTime, long currentTime) {
        return (getMaxDuration() != -1) && ((currentTime - applicationTime) > getMaxDuration() * 1000);
    }

    public void applyTo(StatusHolder holder, int stacks, boolean overrideDuration, boolean overrideStacks) {
        final Pair<Long, Integer> oldStatus = holders.get(holder);
        final long currentTime = System.currentTimeMillis();
        final long oldApplicationTime = oldStatus == null ? currentTime : oldStatus.first();
        final int oldStacks = oldStatus == null ? 0 : oldStatus.second();
       
        final Pair<Long, Integer> newStatus = Pair.of(
            overrideDuration ? currentTime : oldApplicationTime,
            Math.min(overrideStacks ? stacks : oldStacks + stacks, getMaxStacks())
        );
        holders.put(holder, newStatus);
    }

    public void applyTo(StatusHolder holder, int stacks) {
        applyTo(holder, stacks, true, false);
    }

    public void applyTo(StatusHolder holder) {
        applyTo(holder, 1);
    }

    public void clearFrom(StatusHolder holder) {
        holders.remove(holder);
    }
    
}
