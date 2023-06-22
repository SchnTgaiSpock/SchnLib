package io.github.schntgaispock.schnlib;

import java.util.logging.Level;

import javax.annotation.Nullable;

import org.bukkit.plugin.Plugin;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SchnLib {
    
    private static @Getter @Nullable Plugin addon;

    public static void registerAddon(Plugin addon) {
        SchnLib.addon = addon;
    }

    public static void log(Level level, String message, Object... args) {
        if (addon.getLogger() != null) {
            addon.getLogger().log(level, message, args);
        }
    }

    public static void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

    public static void warn(String message, Object... args) {
        log(Level.WARNING, message, args);
    }

    public static void error(String message, Object... args) {
        log(Level.SEVERE, message, args);
    }
}
