package io.github.schntgaispock.schnlib.theme;

import java.util.EnumSet;
import java.util.List;

import org.bukkit.Material;

import io.github.schntgaispock.schnlib.theme.ItemTheme.ItemThemeFlags;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

public class ThemedItemBuilder {

    private final @Getter ChatColor nameColor;
    private final @Getter ChatColor loreColor;
    private final @Getter ChatColor highlightColor;
    private final @Getter ChatColor suffixColor;
    private final @Getter EnumSet<ItemThemeFlags> flags;
    private final @Getter String prefix;

    private @Getter Material material;
    private @Getter String name;
    private @Getter String id;
    private @Getter List<String> lore;
    
    ThemedItemBuilder(ChatColor nameColor, ChatColor loreColor, ChatColor highlightColor, ChatColor suffixColor, EnumSet<ItemThemeFlags> flags, String prefix) {
        this.nameColor = nameColor;
        this.loreColor = loreColor;
        this.highlightColor = highlightColor;
        this.suffixColor = suffixColor;
        this.flags = flags;
        this.prefix = prefix;
    }

    public ThemedItemBuilder material(Material material) {
        this.material = material;
        return this;
    }

    public ThemedItemBuilder name(String name) {
        this.name = name;
        return this;
    }

    public ThemedItemBuilder id(String id) {
        this.id = prefix + "_" + id;
        return this;
    }

    public ThemedItemBuilder lore(String... lore) {
        this.lore.addAll(List.of(lore).stream().map(t -> loreColor + t).toList());
        if (flags.contains(ItemThemeFlags.SPACE_BETWEEN_PARAGRAPHS)) {
            this.lore.add("");
        }
        return this;
    }

    public ThemedItemBuilder space() {
        lore.add("");
        return this;
    }

    public ThemedItemBuilder instruction(String action, String result) {
        return this.instruction(action, result, false);
    }

    public ThemedItemBuilder instruction(String action, String result, boolean addSpace) {
        this.lore.add(highlightColor + action + loreColor + result);
        if (addSpace) {
            this.lore.add("");
        }
        return this;
    }

    public ThemedItemBuilder suffix(String suffix) {
        this.lore.add(suffixColor + suffix);
        return this;
    }

    public SlimefunItemStack build() {
        return new SlimefunItemStack(id, material, name, lore.toArray(String[]::new));
    }

}
