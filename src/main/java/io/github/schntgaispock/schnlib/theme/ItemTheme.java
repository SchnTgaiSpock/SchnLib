package io.github.schntgaispock.schnlib.theme;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

public class ItemTheme {

    public static enum ItemThemeFlags {
        NO_SPACE_AFTER_NAME,
        NO_SPACE_BETWEEN_PARAGRAPHS;
    }

    private @Getter ChatColor nameColor = ChatColor.WHITE;
    private @Getter ChatColor loreColor = ChatColor.GRAY;
    private @Getter ChatColor highlightColor = ChatColor.YELLOW;
    private @Getter ChatColor subtitleColor = ChatColor.GRAY;
    private @Getter ChatColor suffixColor = ChatColor.DARK_AQUA;
    private @Getter EnumSet<ItemThemeFlags> flags = EnumSet.noneOf(ItemThemeFlags.class);
    private @Getter String idPrefix = "";
    
    public ItemTheme(@Nonnull ItemThemeFlags... flags) {
        this.flags.addAll(List.of(flags));
    }

    @Nonnull
    public ItemTheme nameColor(@Nonnull ChatColor color) {
        nameColor = color;
        return this;
    }

    @Nonnull
    public ItemTheme loreColor(@Nonnull ChatColor color) {
        loreColor = color;
        return this;
    }

    @Nonnull
    public ItemTheme highlightColor(@Nonnull ChatColor color) {
        highlightColor = color;
        return this;
    }

    @Nonnull
    public ItemTheme subtitleColor(@Nonnull ChatColor color) {
        subtitleColor = color;
        return this;
    }

    @Nonnull
    public ItemTheme suffixColor(@Nonnull ChatColor color) {
        suffixColor = color;
        return this;
    }

    @Nonnull
    public ItemTheme idPrefix(@Nonnull String prefix) {
        idPrefix = prefix;
        return this;
    }

    @Nonnull
    public ThemedItemBuilder builder() {
        return new ThemedItemBuilder(nameColor, loreColor, highlightColor, subtitleColor, suffixColor, flags, idPrefix);
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public SlimefunItemStack build(String id, Material material, String name, String... lore) {
        return new SlimefunItemStack(idPrefix + "_" + id, material, nameColor + name,
                List.of(lore).stream().map(line -> loreColor + line).toArray(String[]::new));
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public ItemStack build(Material material, String name, String... lore) {
        return new CustomItemStack(material, nameColor + name, List.of(lore).stream().map(line -> loreColor + line).toArray(String[]::new));
    }

}
