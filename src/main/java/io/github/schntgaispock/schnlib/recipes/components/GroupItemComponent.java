package io.github.schntgaispock.schnlib.recipes.components;

import java.util.Set;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.Getter;

/**
 * For recipe slots that have multiple acceptable items.
 * <hr>
 * Examples:
 * <ul>
 * <li>The wood in a bed
 * <li>The cobblestone in a stone sword
 * </ul>
 */
@Getter
public class GroupItemComponent extends RecipeComponent<Set<ItemStack>> {

    private final @Getter NamespacedKey id;

    @ParametersAreNonnullByDefault
    public GroupItemComponent(Set<ItemStack> component, NamespacedKey id) {
        super(component);

        this.id = id;
    }

    @Override
    public boolean isEmpty() {
        return component.isEmpty();
    }

    @Override
    @SuppressWarnings("null")
    public boolean matches(ItemStack item) {
        if (item == null) {
            return component.isEmpty();
        } else {
            for (final ItemStack groupItem : component) {
                if (groupItem instanceof final SlimefunItemStack sfStack) {
                    return SlimefunItem.getById(sfStack.getItemId()).isItem(item);
                } else {
                    return item.isSimilar(groupItem);
                }
            }
        }

        return false;
    }

    @Override
    public ItemStack getDisplayItem() {
        for (final ItemStack itemStack : component) {
            if (itemStack.getType() != Material.AIR)
                return itemStack.clone();
        }

        return new ItemStack(Material.AIR);
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

}
