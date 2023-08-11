package io.github.schntgaispock.schnlib.recipes;

import java.util.Arrays;
import java.util.Set;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.recipes.components.GroupRecipeComponent;
import io.github.schntgaispock.schnlib.recipes.components.RecipeComponent;
import io.github.schntgaispock.schnlib.recipes.components.SingleRecipeComponent;
import io.github.schntgaispock.schnlib.recipes.components.TagRecipeComponent;
import io.github.schntgaispock.schnlib.recipes.outputs.RecipeOutput;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.utils.tags.SlimefunTag;
import lombok.Getter;

@Getter
public class RecipeBuilder {

    private RecipeType type;
    private RecipeComponent<?>[] ingredients;
    private RecipeShape shape;
    private RecipeOutput output;

    public RecipeBuilder() {}

    public RecipeBuilder type(RecipeType type) {
        this.type = type;
        return this;
    }

    @SuppressWarnings("unchecked")
    public RecipeBuilder ingredients(Object... ingredients) {
        this.ingredients = new RecipeComponent<?>[ingredients.length];
        for (int i = 0; i < ingredients.length; i++) {
            final Object ingredient = ingredients[i];
            if (ingredient instanceof final ItemStack item) {
                this.ingredients[i] = new SingleRecipeComponent(item);
            } else if (ingredient instanceof final Material mat) {
                this.ingredients[i] = new SingleRecipeComponent(new ItemStack(mat));
            } else if (ingredient instanceof final Set<?> group) {
                if (group.isEmpty()) {
                    continue;
                }

                if (group.stream().findAny().get() instanceof ItemStack) {
                    this.ingredients[i] = new GroupRecipeComponent((Set<ItemStack>) group);
                }
            } else if (ingredient instanceof final SlimefunTag tag) {
                this.ingredients[i] = new TagRecipeComponent(tag);
            }
        }

        return this;
    }

    public RecipeBuilder ingredients(RecipeComponent<?>... ingredients) {
        this.ingredients = ingredients;
        return this;
    }

    public RecipeBuilder ingredients(ItemStack... ingredients) {
        return ingredients(Arrays.stream(ingredients)
            .map(ingredient -> ingredient == null ? null : new SingleRecipeComponent(ingredient))
            .toArray(RecipeComponent<?>[]::new)
        );
    }
    
}
