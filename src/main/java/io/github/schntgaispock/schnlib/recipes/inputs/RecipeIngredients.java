package io.github.schntgaispock.schnlib.recipes.inputs;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

public abstract class RecipeIngredients {

    public static final RecipeIngredients EMPTY = new RecipeIngredients() {
        @Override
        public boolean matches(ItemStack[] ingredients, boolean consumeIngredients) {
            for (ItemStack item : ingredients) {
                if (item != null && item.getType() != Material.AIR) {
                    return false;
                }
            }
            
            return true;
        }
    };

    public abstract boolean matches(@Nonnull ItemStack[] ingredients, boolean consumeIngredients);
    
}
