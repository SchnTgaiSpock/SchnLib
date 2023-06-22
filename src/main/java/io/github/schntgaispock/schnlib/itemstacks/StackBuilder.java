package io.github.schntgaispock.schnlib.itemstacks;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.Material;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.libraries.commons.lang.Validate;
import net.md_5.bungee.api.ChatColor;

public class StackBuilder {

    private final ItemTheme theme;
    private String id;
    private Material material;
    private String texture;
    private String name;
    private final @Nonnull List<String> lore = new ArrayList<>();

    @ParametersAreNonnullByDefault
    public StackBuilder(ItemTheme theme) {
        this.theme = theme;
    }

    public StackBuilder() {
        this(ItemTheme.GENERIC);
    }

    public StackBuilder id(@Nonnull String id) {
        this.id = id;
        return this;
    }

    public StackBuilder material(@Nonnull Material material) {
        Validate.isTrue(texture == null, "Cannot set a material for this StackBuilder because it already has a texture!");
        Validate.isTrue(material == null, "This StackBuilder already has a material!");
        this.material = material;
        return this;
    }

    public StackBuilder texture(@Nonnull String texture) {
        Validate.isTrue(material == null, "Cannot set a texure for this StackBuilder because it already has a mateiral!");
        Validate.isTrue(texture == null, "This StackBuilder already has a texture!");
        this.texture = texture;
        return this;
    }

    public StackBuilder name(@Nonnull String name) {
        Validate.isTrue(name == null, "This StackBuilder already has a name!");
        this.name = name;
        return this;
    }

    @ParametersAreNonnullByDefault
    public StackBuilder name(ChatColor color, String name) {
        return name(color.toString() + name);
    }

    @ParametersAreNonnullByDefault
    public StackBuilder lore(ChatColor color, String... lore) {
        if (theme.hasLoreSpace())
            this.lore.add("");
        for (String line : lore) {
            this.lore.add(color.toString() + line);
        }

        return this;
    }

    public StackBuilder lore(@Nonnull String... lore) {
        return lore(theme.getLoreColor(), lore);
    }

    public StackBuilder info(@Nonnull String... lore) {
        return lore(theme.getInfoColor(), lore);
    }

    public StackBuilder warn(@Nonnull String... lore) {
        return lore(theme.getWarnColor(), lore);
    }

    public StackBuilder newline() {
        this.lore.add("");
        return this;
    }

    public SlimefunItemStack build() {
        Validate.notNull(id, "Must set id with `id()!`");
        Validate.notNull(name, "Must set name with `name()!`");
        if (texture == null) {
            return new SlimefunItemStack(id, material, name, lore.toArray(String[]::new));
        } else {
            Validate.notNull(material, "Must set material or texture with `material()` or `texture()`!");
            return new SlimefunItemStack(id, texture, name, lore.toArray(String[]::new));
        }
    }

}
