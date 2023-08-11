package io.github.schntgaispock.schnlib.utils;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNullableByDefault;

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
            return item.hashCode();
        }
    }

    /**
     * Consistently orders item stacks (for sorting). Its order is meaningless
     * @param item1 The first item to compare
     * @param item2 The second item to compare
     * @return The result of the comparison (-1, 0, or 1)
     */
    @ParametersAreNullableByDefault
    public static int compare(ItemStack item1, ItemStack item2) {
        // TODO: This is inefficient as items may be hashed multiple times during a single sort
        return Integer.compare(hashIgnoreAmount(item1), hashIgnoreAmount(item2));
    }

}
