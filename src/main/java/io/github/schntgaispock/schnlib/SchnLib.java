package io.github.schntgaispock.schnlib;

import java.util.EnumSet;
import java.util.Set;
import java.util.logging.Level;

import javax.annotation.Nullable;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.Plugin;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SchnLib {

    /**
     * Options that affects how SchnLib runs
     */
    public static enum SchnLibOptions {
    }

    private static @Nullable Plugin addon;
    /**
     * Gets the currently enabled options for SchnLib
     */
    private static @Getter Set<SchnLibOptions> options = EnumSet.noneOf(SchnLibOptions.class);

    /**
     * Sets up SchnLib with your plugin. SchnLib registers listeners and namespaced
     * keys in your plugin's name. Currently, only the menu and music systems
     * require
     * a plugin instance.
     * 
     * @param addon A plugin instance to register listeners and namespaced keys with
     */
    public static void setup(Plugin addon) {
        SchnLib.addon = addon;
    }

    /**
     * Returns the addon that was given to SchnLib via SchnLib#setup, or throws an
     * error if no addon was given.
     * 
     * @param errorMessage The message to give when no addon is found
     * @return The addon that was given to SchnLib via SchnLib#setup
     * @throws IllegalStateException If no addon was given to SchnLib to use
     */
    public static Plugin getAddon(String errorMessage) throws IllegalStateException {
        if (addon == null) {
            throw new IllegalStateException(errorMessage);
        }
        return addon;
    }

    /**
     * Returns the addon that was given to SchnLib via SchnLib#setup, or throws an
     * error if no addon was given.
     * 
     * @return The addon that was given to SchnLib via SchnLib#setup
     * @throws IllegalStateException If no addon was given to SchnLib to use
     */
    public static Plugin getAddon() throws IllegalStateException {
        return getAddon("Unable to retrive addon! Make sure to call SchnLib#setup() with your plugin instance!");
    }

    /**
     * Logs a message
     * 
     * @param level   The level of the message
     * @param message The message to log
     * @param args    Format specifier string arguments
     */
    public static void log(Level level, String message, Object... args) {
        getAddon().getLogger().log(level, message, args);
    }

    /**
     * Logs an info message
     * 
     * @param message The message to log
     * @param args    Format specifier string arguments
     */
    public static void info(String message, Object... args) {
        log(Level.INFO, message, args);
    }

    /**
     * Logs an warning message
     * 
     * @param message The message to log
     * @param args    Format specifier string arguments
     */
    public static void warn(String message, Object... args) {
        log(Level.WARNING, message, args);
    }

    /**
     * Logs an error message
     * 
     * @param message The message to log
     * @param args    Format specifier string arguments
     */
    public static void error(String message, Object... args) {
        log(Level.SEVERE, message, args);
    }

    public static NamespacedKey key(String key) {
        return new NamespacedKey(addon, key);
    }

}
