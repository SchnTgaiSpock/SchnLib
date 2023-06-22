package io.github.schntgaispock.schnlib.itemstacks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.Getter;

/**
 * ThemedItemStack
 */
public class ThemedItemStack extends SlimefunItemStack {

    private static @Getter String idPrefix;

    @ParametersAreNonnullByDefault
    private ThemedItemStack(String id, Material type, String name, String... lore) {
        super(idPrefix + id.toUpperCase(), type, name, lore);
    }

    @ParametersAreNonnullByDefault
    private ThemedItemStack(String id, Material type, String name, List<String> lore) {
        this(id, type, name, lore.toArray(String[]::new));
    }

    @ParametersAreNonnullByDefault
    private ThemedItemStack(String id, Material type, String name) {
        super(idPrefix + id.toUpperCase(), type, name);
    }

    @ParametersAreNonnullByDefault
    public static ThemedItemStack of(ItemTheme theme, String id, Material type, String name, String... lore) {
        final List<String> fLore = new ArrayList<>();
        if (theme.hasLoreSpace() && lore.length > 0)
            fLore.add("");

        for (String line : lore) {
            fLore.add(theme.getLoreColor() + line);
        }

        if (theme.getSuffixText() != null) {
            fLore.add("");
            fLore.add(theme.getSuffixText());
        }

        return new ThemedItemStack(id, type, theme.getNameColor().toString() + name, fLore);
    }

    @ParametersAreNonnullByDefault
    public static ThemedItemStack of(ItemTheme theme, String id, Material type, String name) {
        return of(theme, id, type, name);
    }

    @ParametersAreNonnullByDefault
    public static ThemedItemStack of(String id, Material type, String name, String lore) {
        return of(ItemTheme.GENERIC, id, type, name, lore);
    }

    @ParametersAreNonnullByDefault
    public static ThemedItemStack of(String id, Material type, String name) {
        return of(ItemTheme.GENERIC, id, type, name);
    }

    public static void setIdPrefix(String prefix) {
        idPrefix = prefix.toUpperCase() + "_";
    }

}