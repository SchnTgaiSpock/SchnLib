package io.github.schntgaispock.schnlib.recipes.components;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.libraries.dough.items.ItemUtils;
import lombok.Getter;

@Getter
public class SingleRecipeComponent implements RecipeComponent<ItemStack> {

    private final ItemStack component;

    public SingleRecipeComponent(@Nonnull ItemStack component) {
        this.component = component;
    }

    @Override
    public boolean matches(@Nullable ItemStack item) {
        return ItemUtils.canStack(component, item);
    }

    @Override
    public boolean isEmpty() {
        return component.getType() == Material.AIR;
    }

    @Override
    public ItemStack getDisplayItem() {
        return component.clone();
    }

    @Override
    public String toString() {
        return "SingleRecipeComponent(" + getComponent().toString() + ")";
    }
    
}
