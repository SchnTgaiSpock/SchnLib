package io.github.schntgaispock.schnlib.recipes.outputs;

import org.bukkit.inventory.ItemStack;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ItemRecipeOutput implements RecipeOutput {

    private final ItemStack[] outputs;
    
}
