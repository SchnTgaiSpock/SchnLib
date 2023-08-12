package io.github.schntgaispock.schnlib.recipes.outputs;

import org.bukkit.inventory.ItemStack;

@FunctionalInterface
public interface RecipeOutput {

    public static RecipeOutput EMPTY = new RecipeOutput() {
        @Override
        public ItemStack[] getOutputs() {
            return new ItemStack[0];
        }
    };
    
    public ItemStack[] getOutputs();

}
