package io.github.schntgaispock.schnlib.theme;

import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.theme.ItemTheme.ItemThemeFlags;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import net.md_5.bungee.api.ChatColor;

@RequiredArgsConstructor(access = AccessLevel.PACKAGE)
public class ThemedItemBuilder {

    private final @Getter ChatColor nameColor;
    private final @Getter ChatColor loreColor;
    private final @Getter ChatColor highlightColor;
    private final @Getter ChatColor subtitleColor;
    private final @Getter ChatColor suffixColor;
    private final @Getter EnumSet<ItemThemeFlags> flags;
    private final @Getter String prefix;

    private @Getter @Nullable Material material;
    private @Getter @Nullable String texture;
    private @Getter String name;
    private @Getter String id;
    private @Getter List<String> lore;
    private boolean hasSubtitle = false;

    @Nonnull
    public ThemedItemBuilder material(@Nonnull Material material) {
        if (texture != null) {
            throw new IllegalStateException("Cannot set material because texture is already set!");
        }
        this.material = material;
        return this;
    }

    @Nonnull
    public ThemedItemBuilder texture(@Nonnull String texture) {
        if (material != null) {
            throw new IllegalStateException("Cannot set texture because material is already set!");
        }
        this.texture = texture;
        return this;
    }

    @Nonnull
    public ThemedItemBuilder name(@Nonnull String name) {
        this.name = name;
        return this;
    }

    @Nonnull
    public ThemedItemBuilder id(@Nonnull String id) {
        this.id = prefix != null ? prefix + "_" + id : id;
        return this;
    }

    @Nonnull
    public ThemedItemBuilder subtitle(@Nonnull String subtitle) {
        lore.add(subtitleColor + subtitle);
        if (!flags.contains(ItemThemeFlags.NO_SPACE_AFTER_NAME)) {
            lore.add("");
        }
        hasSubtitle = true;
        return this;
    }

    @Nonnull
    public ThemedItemBuilder lore(@Nonnull String... lore) {
        this.lore.addAll(List.of(lore).stream().map(t -> loreColor + t).toList());
        if (!flags.contains(ItemThemeFlags.NO_SPACE_BETWEEN_PARAGRAPHS)) {
            this.lore.add("");
        }
        return this;
    }

    @Nonnull
    public ThemedItemBuilder space() {
        lore.add("");
        return this;
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public ThemedItemBuilder instruction(String action, String result) {
        return this.instruction(action, result, false);
    }

    @Nonnull
    @ParametersAreNonnullByDefault
    public ThemedItemBuilder instruction(String action, String result, boolean addSpace) {
        this.lore.add(highlightColor + action + loreColor + result);
        if (addSpace) {
            this.lore.add("");
        }
        return this;
    }

    @Nonnull
    public ThemedItemBuilder suffix(@Nonnull String suffix) {
        this.lore.add(suffixColor + suffix);
        return this;
    }

    @Nonnull
    private String[] getFormattedLore() {
        final List<String> formattedLore = new ArrayList<>();
        if (!hasSubtitle && !flags.contains(ItemThemeFlags.NO_SPACE_AFTER_NAME)) {
            formattedLore.add("");
        }
        formattedLore.addAll(lore);
        return formattedLore.toArray(String[]::new);
    }

    @Nonnull
    public SlimefunItemStack build() {
        Validate.notNull(id, "Cannot build a SlimefunItemStack without an id!");
        if (material == null) {
            Validate.notNull(texture, "Cannot build a SlimefunItemStack without a material or texture!");
            return new SlimefunItemStack(id, texture, name, getFormattedLore());
        }
        return new SlimefunItemStack(id, material, name, getFormattedLore());
    }

    @Nonnull
    public ItemStack buildRegular() {
        Validate.notNull(material, "Cannot build a regular ItemStack without a material!");
        return new CustomItemStack(material, name, getFormattedLore());
    }

}
