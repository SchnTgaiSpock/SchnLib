package io.github.schntgaispock.schnlib.recipes.inputs;

import java.util.Collections;
import java.util.List;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.collections.Pair;

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

        @Override
        public Pair<ItemStack[], List<ItemStack>> getGuideDisplay() {
            return Pair.of(new ItemStack[9], Collections.emptyList());
        }

    };

    public abstract boolean matches(@Nonnull ItemStack[] ingredients, boolean consumeIngredients);
    public abstract Pair<ItemStack[], List<ItemStack>> getGuideDisplay();
    
}
