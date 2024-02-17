package io.github.schntgaispock.schnlib.slimefun.itemgroups;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.SchnLib;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.SubItemGroup;

/**
 * Modified from {@link SubItemGroup}
 */
public class ChildGroup extends ItemGroup {

    @ParametersAreNonnullByDefault
    public ChildGroup(String key, ItemStack item) {
        this(key, item, 3);
    }

    @ParametersAreNonnullByDefault
    public ChildGroup(String key, ItemStack item, int tier) {
        super(SchnLib.key(key), item, tier);
    }

    @Override
    public final boolean isVisible(@Nonnull Player p) {
        return false;
    }

    @Override
    public final boolean isAccessible(@Nonnull Player p) {
        return true;
    }

    @Override
    public final void register(@Nonnull SlimefunAddon addon) {}
}
