package io.github.schntgaispock.schnlib.status;

import java.util.HashMap;
import java.util.Map;
import java.util.function.BiPredicate;

import javax.annotation.Nullable;

import org.bukkit.Bukkit;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;

import io.github.schntgaispock.schnlib.SchnLib;
import io.github.schntgaispock.schnlib.collections.Pair;
import lombok.Getter;

public class Status implements Listener {

    private final @Getter int maxStacks = 1;
    /**
     * Maximum duration in seconds
     */
    private final @Getter int maxDuration = -1;
    public final @Getter Map<StatusHolder<?>, Pair<Long, Integer>> holders = new HashMap<>();

    public void regsiter() {
        Bukkit.getPluginManager().registerEvents(this, SchnLib.getAddon());
    }

    public Map<StatusHolder<?>, Pair<Long, Integer>> getHolders(@Nullable BiPredicate<StatusHolder<?>, Pair<Long, Integer>> filter) {
        final Map<StatusHolder<?>, Pair<Long, Integer>> filteredHolders = new HashMap<>();
        final long currentTime = System.currentTimeMillis();

        for (Map.Entry<StatusHolder<?>, Pair<Long, Integer>> holder : holders.entrySet()) {
            if (!isExpired(holder.getValue().first(), currentTime) 
                && filter != null 
                && filter.test(holder.getKey(), holder.getValue())
            ) {
                filteredHolders.put(holder.getKey(), holder.getValue());
            }
        }

        return filteredHolders;
    }

    private boolean isExpired(long applicationTime, long currentTime) {
        return (getMaxDuration() != -1) && ((currentTime - applicationTime) > getMaxDuration() * 1000);
    }

    public void applyTo(StatusHolder<?> holder, int stacks, boolean overrideDuration, boolean overrideStacks) {
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

    public void applyTo(StatusHolder<?> holder, int stacks) {
        applyTo(holder, stacks, true, false);
    }

    public void applyTo(StatusHolder<?> holder) {
        applyTo(holder, 1);
    }

    public void clear(StatusHolder<?> holder) {
        holders.remove(holder);
    }

    public static void main(String[] args) {
        new Status() {
            @EventHandler
            public void test() {

            }
        }.regsiter();
    }
    
}
