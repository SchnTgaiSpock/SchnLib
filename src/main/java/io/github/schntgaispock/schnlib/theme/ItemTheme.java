package io.github.schntgaispock.schnlib.theme;

import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.Getter;
import lombok.Setter;
import net.md_5.bungee.api.ChatColor;

/**
 * Stores info about colors/flags that are shared between items
 */
public class ItemTheme {

    public static enum ItemThemeFlags {
        NO_SPACE_AFTER_NAME,
        NO_SPACE_BETWEEN_PARAGRAPHS;
    }

    private static @Getter @Setter @Nullable String idPrefix = null;

    private @Getter ChatColor nameColor = ChatColor.WHITE;
    private @Getter ChatColor loreColor = ChatColor.GRAY;
    private @Getter ChatColor highlightColor = ChatColor.YELLOW;
    private @Getter ChatColor subtitleColor = ChatColor.GRAY;
    private @Getter ChatColor suffixColor = ChatColor.DARK_AQUA;
    private @Getter EnumSet<ItemThemeFlags> flags = EnumSet.noneOf(ItemThemeFlags.class);
    
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
    public static String getId(@Nonnull String id) {
        return idPrefix != null ? idPrefix + "_" + id : id;
    }

    /**
     * Return a builder that is used to build individual items
     * 
     * @return A new {@link ThemedItemBuilder} with this themes colors
     */
    @Nonnull
    public ThemedItemBuilder builder() {
        return new ThemedItemBuilder(nameColor, loreColor, highlightColor, subtitleColor, suffixColor, flags);
    }

    /**
     * Build a {@link SlimefunItemStack} with the given parameters and applying theme colors
     * 
     * @param id The id of this item
     * @param material The material of this item
     * @param name The name of this item
     * @param lore The lore of this item
     * @return A new SlimefunItemStack
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public SlimefunItemStack build(String id, Material material, String name, String... lore) {
        return new SlimefunItemStack(getId(id), material, nameColor + name,
                List.of(lore).stream().map(line -> loreColor + line).toArray(String[]::new));
    }

    /**
     * Build a {@link SlimefunItemStack} with the given parameters and applying theme colors
     * 
     * @param id The id of this item. Will prepend idPrefix for the final id
     * @param texture The texture of this item
     * @param name The name of this item
     * @param lore The lore of this item
     * @return A new SlimefunItemStack
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public SlimefunItemStack build(String id, String texture, String name, String... lore) {
        return new SlimefunItemStack(idPrefix + "_" + id, texture, nameColor + name,
                List.of(lore).stream().map(line -> loreColor + line).toArray(String[]::new));
    }


    /**
     * Build an {@link ItemStack} with the given parameters and applying theme colors
     * 
     * @param material The material of this item
     * @param name The name of this item
     * @param lore The lore of this item
     * @return A new ItemStack
     */
    @Nonnull
    @ParametersAreNonnullByDefault
    public ItemStack build(Material material, String name, String... lore) {
        return new CustomItemStack(material, nameColor + name, List.of(lore).stream().map(line -> loreColor + line).toArray(String[]::new));
    }

}
