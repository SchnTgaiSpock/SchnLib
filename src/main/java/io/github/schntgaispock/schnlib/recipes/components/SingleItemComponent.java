package io.github.schntgaispock.schnlib.recipes.components;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.utils.StackUtils;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.Getter;

/**
 * For recipe slots that require a certain item
 * 
 * Examples:
 * - The wool in a bed
 * - The stick in a stone sword
 */
@Getter
public class SingleItemComponent extends RecipeComponent<ItemStack> {

    public SingleItemComponent(@Nonnull ItemStack component) {
        super(component);
    }

    @Override
    public boolean isEmpty() {
        return component.getType() == Material.AIR;
    }

    // SingleRecipeComponents do not have to deal with group components in recipes
    @Override
    public boolean matches(ItemStack item) {
        if (item == null) {
            return component.getType() == Material.AIR;
        } else if (component instanceof final SlimefunItemStack sfStack) {
            return SlimefunItem.getById(sfStack.getItemId()).isItem(item);
        } else {
            return item.isSimilar(component);
        }
    }

    @Override
    public ItemStack getDisplayItem() {
        return component.clone();
    }

    @Override
    public int hashCode() {
        return StackUtils.hashIgnoreAmount(component);
    }

}
