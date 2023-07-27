package io.github.schntgaispock.schnlib.utils;

import javax.annotation.Nullable;

import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.experimental.UtilityClass;

@UtilityClass
public class StackUtil {

    public static int hashIgnoreAmount(@Nullable ItemStack item) {
        if (item == null) return 0;

        final ItemStack clone = item.clone();
        clone.setAmount(1);
        return clone.hashCode();
    }
    
    public static int recipeHash(@Nullable ItemStack item) {
        if (item == null) {
            return Integer.MAX_VALUE;
        }

        if (item instanceof final SlimefunItemStack sfItemStack) {
            return sfItemStack.getItemId().hashCode();
        }

        final SlimefunItem sfItem = SlimefunItem.getByItem(item);
        if (sfItem != null) {
            return sfItem.getId().hashCode();
        } else {
            return item.getType().hashCode();
        }
    }

    public static int compare(ItemStack item1, ItemStack item2) {
        return Integer.compare(recipeHash(item1), recipeHash(item2));
    }

}
