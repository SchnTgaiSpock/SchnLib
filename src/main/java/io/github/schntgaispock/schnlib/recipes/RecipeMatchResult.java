package io.github.schntgaispock.schnlib.recipes;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum RecipeMatchResult {
    
    SUCCESS(true, true),
    NO_RESEARCH(true, false),
    NO_PERMISSION(true, false),
    NO_MATCH(false, false);

    private final @Getter boolean match;
    private final boolean canCraft;

    public boolean canCraft() {
        return canCraft;
    }

}
