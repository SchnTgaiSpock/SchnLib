package io.github.schntgaispock.schnlib.slimefun.itemgroups;

import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import io.github.schntgaispock.schnlib.SchnLib;
import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.FlexItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.groups.NestedItemGroup;
import io.github.thebusybiscuit.slimefun4.api.player.PlayerProfile;
import io.github.thebusybiscuit.slimefun4.core.guide.GuideHistory;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuide;
import io.github.thebusybiscuit.slimefun4.core.guide.SlimefunGuideMode;
import io.github.thebusybiscuit.slimefun4.core.services.sounds.SoundEffect;
import io.github.thebusybiscuit.slimefun4.implementation.Slimefun;
import io.github.thebusybiscuit.slimefun4.implementation.guide.SurvivalSlimefunGuide;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.github.thebusybiscuit.slimefun4.utils.ChestMenuUtils;
import lombok.Getter;
import me.mrCookieSlime.CSCoreLibPlugin.general.Inventory.ChestMenu;
import net.md_5.bungee.api.ChatColor;

/**
 * Modified from {@link NestedItemGroup}
 */
public class ParentGroup extends FlexItemGroup {

    private static final int GROUP_SIZE = 36;

    private boolean visible;
    private @Getter ItemGroup[] children;

    public ParentGroup(String key, ItemStack item, ItemGroup... children) {
        this(key, item, 3, children);
    }

    public ParentGroup(String key, ItemStack item, int tier, ItemGroup... children) {
        super(SchnLib.key(key), item, tier);
        this.visible = true;
        this.children = children;
    }

    @Override
    public boolean isVisible(Player player, PlayerProfile profile, SlimefunGuideMode guideMode) {
        return visible && (guideMode == SlimefunGuideMode.SURVIVAL_MODE);
    }

    public ParentGroup setVisible(boolean visible) {
        this.visible = visible;
        return this;
    }

    @Override
    @ParametersAreNonnullByDefault
    public void open(Player p, PlayerProfile profile, SlimefunGuideMode mode) {
        openGuide(p, profile, mode, 1);
    }

    @Deprecated
    @ParametersAreNonnullByDefault
    private void openGuide(Player p, PlayerProfile profile, SlimefunGuideMode mode, int page) {
        final GuideHistory history = profile.getGuideHistory();

        if (mode == SlimefunGuideMode.SURVIVAL_MODE) {
            history.add(this, page);
        }

        final ChestMenu menu = new ChestMenu(Slimefun.getLocalization().getMessage(p, "guide.title.main"));
        final SurvivalSlimefunGuide guide = (SurvivalSlimefunGuide) Slimefun.getRegistry().getSlimefunGuide(mode);

        menu.setEmptySlotsClickable(false);
        menu.addMenuOpeningHandler(SoundEffect.GUIDE_BUTTON_CLICK_SOUND::playFor);
        guide.createHeader(p, profile, menu);

        menu.addItem(1, new CustomItemStack(ChestMenuUtils.getBackButton(p, "", ChatColor.GRAY + Slimefun.getLocalization().getMessage(p, "guide.back.guide"))));
        menu.addMenuClickHandler(1, (pl, s, is, action) -> {
            SlimefunGuide.openMainMenu(profile, mode, history.getMainMenuPage());
            return false;
        });

        int index = 9;
        int target = (GROUP_SIZE * (page - 1));
        while (target < children.length&& index < GROUP_SIZE + 9) {

            final ItemGroup itemGroup = children[target];
            menu.addItem(index, itemGroup.getItem(p));
            menu.addMenuClickHandler(index, (pl, slot, item, action) -> {
                SlimefunGuide.openItemGroup(profile, itemGroup, mode, 1);
                return false;
            });

            target++;
            index++;
        }

        int pages = (children.length - 1) / GROUP_SIZE + 1;

        menu.addItem(46, ChestMenuUtils.getPreviousButton(p, page, pages));
        menu.addMenuClickHandler(46, (pl, slot, item, action) -> {
            int next = page - 1;

            if (next != page && next > 0) {
                openGuide(p, profile, mode, next);
            }

            return false;
        });

        menu.addItem(52, ChestMenuUtils.getNextButton(p, page, pages));
        menu.addMenuClickHandler(52, (pl, slot, item, action) -> {
            int next = page + 1;

            if (next != page && next <= pages) {
                openGuide(p, profile, mode, next);
            }

            return false;
        });

        menu.open(p);
    }

    @Override
    public void register(SlimefunAddon addon) {
        super.register(addon);
        for (ItemGroup group : children) {
            if (!group.isRegistered()) {
                group.register(addon);
            }
        }
    }
    
}
