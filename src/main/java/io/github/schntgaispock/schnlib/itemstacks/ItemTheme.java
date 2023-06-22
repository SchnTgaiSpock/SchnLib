package io.github.schntgaispock.schnlib.itemstacks;

import javax.annotation.Nullable;
import javax.annotation.ParametersAreNonnullByDefault;

import lombok.Getter;
import net.md_5.bungee.api.ChatColor;

public class ItemTheme {

    public static final ItemTheme AUTO = new ItemTheme();
    public static final ItemTheme GENERIC = new ItemTheme();
    public static final ItemTheme SPECIAL = new ItemTheme();

    private final @Getter ChatColor nameColor;
    private final @Getter ChatColor loreColor;
    private final @Getter ChatColor accentColor;
    private final @Getter ChatColor infoColor;
    private final @Getter ChatColor warnColor;
    private final @Getter String suffixText;
    private final boolean hasLoreSpace;

    @ParametersAreNonnullByDefault
    public ItemTheme(ChatColor nameColor, ChatColor loreColor, ChatColor accentColor, ChatColor infoColor, ChatColor warnColor, @Nullable String suffixtext, boolean hasLoreLine) {
        this.nameColor = nameColor;
        this.loreColor = loreColor;
        this.accentColor = accentColor;
        this.infoColor = infoColor;
        this.warnColor = warnColor;
        this.suffixText = suffixtext;
        this.hasLoreSpace = hasLoreLine;
    }

    @ParametersAreNonnullByDefault
    public ItemTheme(ChatColor nameColor, ChatColor loreColor, ChatColor accentColor, ChatColor infoColor, ChatColor warnColor, @Nullable String suffixText) {
        this(nameColor, loreColor, accentColor, infoColor, warnColor, suffixText, true);
    }

    @ParametersAreNonnullByDefault
    public ItemTheme(ChatColor nameColor, ChatColor loreColor, ChatColor accentColor, ChatColor infoColor, ChatColor warnColor) {
        this(nameColor, loreColor, accentColor, infoColor, warnColor, null);
    }

    @ParametersAreNonnullByDefault
    public ItemTheme(ChatColor nameColor, ChatColor loreColor, ChatColor accentColor, ChatColor infoColor) {
        this(nameColor, loreColor, accentColor, infoColor, ChatColor.RED);
    }

    @ParametersAreNonnullByDefault
    public ItemTheme(ChatColor nameColor, ChatColor loreColor, ChatColor accentColor) {
        this(nameColor, loreColor, accentColor, ChatColor.YELLOW);
    }

    @ParametersAreNonnullByDefault
    public ItemTheme(ChatColor nameColor, ChatColor loreColor) {
        this(nameColor, loreColor, ChatColor.BLUE);
    }

    @ParametersAreNonnullByDefault
    public ItemTheme(ChatColor nameColor) {
        this(nameColor, ChatColor.GRAY);
    }

    public ItemTheme() {
        this(ChatColor.WHITE);
    }

    public boolean hasLoreSpace() {
        return hasLoreSpace;
    }

}
