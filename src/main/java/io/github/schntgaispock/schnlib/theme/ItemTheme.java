package io.github.schntgaispock.schnlib.theme;

import java.util.EnumSet;
import java.util.List;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

public class ItemTheme {

    public static enum ItemThemeFlags {
        SPACE_AFTER_NAME,
        SPACE_BETWEEN_PARAGRAPHS;
    }

    private @Getter ChatColor nameColor = ChatColor.WHITE;
    private @Getter ChatColor loreColor = ChatColor.GRAY;
    private @Getter ChatColor highlightColor = ChatColor.YELLOW;
    private @Getter ChatColor suffixColor = ChatColor.DARK_AQUA;
    private @Getter EnumSet<ItemThemeFlags> flags = EnumSet.noneOf(ItemThemeFlags.class);
    private @Getter String prefix = "";

    public ItemTheme nameColor(ChatColor color) {
        nameColor = color;
        return this;
    }

    public ItemTheme loreColor(ChatColor color) {
        loreColor = color;
        return this;
    }

    public ItemTheme highlightColor(ChatColor color) {
        highlightColor = color;
        return this;
    }

    public ItemTheme suffixColor(ChatColor color) {
        suffixColor = color;
        return this;
    }

    public ItemTheme flags(ItemThemeFlags... flags) {
        this.flags.addAll(List.of(flags));
        return this;
    }

    public ItemTheme prefix(String prefix) {
        this.prefix = prefix;
        return this;
    }

    public ThemedItemBuilder builder() {
        return new ThemedItemBuilder(nameColor, loreColor, highlightColor, suffixColor, flags, prefix);
    }

    public SlimefunItemStack build(String id, Material material, String name, String... lore) {
        return new SlimefunItemStack(id, material, nameColor + name,
                List.of(lore).stream().map(line -> loreColor + line).toArray(String[]::new));
    }

}
