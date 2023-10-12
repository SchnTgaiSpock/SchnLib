package io.github.schntgaispock.schnlib.recipes.inputs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.collections.IntPair;
import io.github.schntgaispock.schnlib.collections.Pair;
import io.github.schntgaispock.schnlib.recipes.RecipeShape;
import io.github.schntgaispock.schnlib.recipes.components.RecipeComponent;
import io.github.schntgaispock.schnlib.utils.CollectionUtil;
import lombok.Getter;

@Getter
public class CraftingGrid extends RecipeIngredients {

    private final RecipeComponent<?>[] ingredients;
    private final int width;
    private final int height;
    private final RecipeShape shape;

    public CraftingGrid(RecipeComponent<?>[] ingredients, RecipeShape shape) {
        this.shape = shape;
        switch (shape) {
            case IDENTICAL:
                this.ingredients = ingredients;
                height = 3;
                width = 3;
                break;

            case TRANSLATED:
                final var result = CollectionUtil.strip(
                        ingredients,
                        3, 3,
                        comp -> comp == null || comp.isEmpty());
                System.out.println(result.first().getClass().getName());
                System.out.println(RecipeComponent[].class.getName());
                this.ingredients = result.first().toArray(RecipeComponent<?>[]::new);
                width = result.second().first();
                height = result.second().second();
                break;

            default:
                this.ingredients = Arrays
                        .stream(ingredients)
                        .filter(comp -> comp == null || comp.isEmpty())
                        .toArray(RecipeComponent<?>[]::new);
                height = 0;
                width = 0;
                break;
        }
    }

    @Override
    public boolean matches(@Nonnull ItemStack[] ingredients, boolean consumeIngredients) {
        return switch (shape) {
            case IDENTICAL -> matchIdentical(ingredients, consumeIngredients);
            case TRANSLATED -> matchTranslated(ingredients, consumeIngredients);
            case SHUFFLED -> matchShuffled(ingredients, consumeIngredients);
            case CONTAINING -> matchContaining(ingredients, consumeIngredients);
            default -> false;
        };
    }

    public boolean matchIdentical(@Nonnull ItemStack[] ingredients, boolean consumeIngredients) {
        if (ingredients.length != width * height) {
            return false;
        }

        for (int i = 0; i < ingredients.length; i++) {
            if (!this.ingredients[i].matches(ingredients[i])) {
                return false;
            }
        }

        if (consumeIngredients) {
            for (ItemStack item : ingredients) {
                if (item != null) {
                    item.setAmount(item.getAmount() - 1);
                }
            }
        }

        return true;
    }

    public boolean matchTranslated(@Nonnull ItemStack[] ingredients, boolean consumeIngredients) {
        if (ingredients.length != 9) {
            return false;
        }

        final Pair<List<ItemStack>, IntPair> result = CollectionUtil.strip(
                ingredients,
                3, 3,
                item -> item == null || item.getType() == Material.AIR);
        final ItemStack[] reduced = result.first().toArray(ItemStack[]::new);

        return matchIdentical(reduced, consumeIngredients);
    }

    public boolean matchShuffled(@Nonnull ItemStack[] ingredients, boolean consumeIngredients) {
        final ItemStack[] givenIngredients = Arrays
                .stream(ingredients)
                .filter(ingredient -> ingredient == null || ingredient.getType() == Material.AIR)
                .toArray(ItemStack[]::new);

        if (givenIngredients.length != this.ingredients.length) {
            return false;
        }

        final Set<Integer> recipeIngredientsMatched = new HashSet<>();

        for (ItemStack givenIngredient : givenIngredients) {
            for (int i = 0; i < this.ingredients.length; i++) {
                if (recipeIngredientsMatched.contains(i)) {
                    continue;
                }

                if (this.ingredients[i].matches(givenIngredient)) {
                    recipeIngredientsMatched.add(i);
                }
            }
        }

        final boolean matched = recipeIngredientsMatched.size() == this.ingredients.length;

        if (matched && consumeIngredients) {
            for (ItemStack item : givenIngredients) {
                item.setAmount(item.getAmount());
            }
        }

        return matched;
    }

    public boolean matchContaining(@Nonnull ItemStack[] ingredients, boolean consumeIngredients) {
        final ItemStack[] givenIngredients = Arrays
                .stream(ingredients)
                .filter(ingredient -> ingredient == null || ingredient.getType() == Material.AIR)
                .toArray(ItemStack[]::new);

        if (givenIngredients.length < this.ingredients.length) {
            return false;
        }

        final Set<Integer> givenIngredientsMatched = new HashSet<>();
        final Set<Integer> recipeIngredientsMatched = new HashSet<>();

        for (int i = 0; i < givenIngredients.length; i++) {
            for (int j = 0; j < this.ingredients.length; j++) {
                if (recipeIngredientsMatched.contains(j)) {
                    continue;
                }

                if (this.ingredients[j].matches(givenIngredients[i])) {
                    givenIngredientsMatched.add(i);
                    recipeIngredientsMatched.add(j);
                }
            }
        }

        final boolean matched = recipeIngredientsMatched.size() == this.ingredients.length;

        if (matched && consumeIngredients) {
            givenIngredientsMatched.stream().forEach(i -> {
                final ItemStack item = givenIngredients[i];
                item.setAmount(item.getAmount() - 1);
            });
        }

        return matched;
    }

    @Override
    public Pair<ItemStack[], List<ItemStack>> getGuideDisplay() {
        return Pair.of(
            Arrays.stream(getIngredients())
                .map(comp -> comp == null ? null : comp.getDisplayItem())
                .toArray(ItemStack[]::new),
            Collections.emptyList()
        );
    }

}
