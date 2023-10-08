package io.github.schntgaispock.schnlib.utils;

import javax.annotation.Nonnull;
import javax.annotation.ParametersAreNonnullByDefault;

import org.bukkit.NamespacedKey;
import org.bukkit.persistence.PersistentDataContainer;
import org.bukkit.persistence.PersistentDataHolder;
import org.bukkit.persistence.PersistentDataType;

import lombok.experimental.UtilityClass;

@UtilityClass
public class PDCUtil {
    
    @ParametersAreNonnullByDefault
    public static byte getByte(PersistentDataHolder holder, NamespacedKey key, byte def) {
        return getByte(holder, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static byte getByte(PersistentDataContainer pdc, NamespacedKey key, byte def) {
        return getByte(pdc, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static byte getByte(PersistentDataHolder holder, NamespacedKey key, byte def, boolean setDefaultIfNone) {
        return getByte(holder.getPersistentDataContainer(), key, def, setDefaultIfNone);
    }
    @ParametersAreNonnullByDefault
    public static byte getByte(PersistentDataContainer pdc, NamespacedKey key, byte def, boolean setDefaultIfNone) {
        if (pdc.has(key, PersistentDataType.BYTE)) {
            return pdc.get(key, PersistentDataType.BYTE);
        } else if (setDefaultIfNone) {
            pdc.set(key, PersistentDataType.BYTE, def);
        }

        return def;
    }
    
    @ParametersAreNonnullByDefault
    public static short getShort(PersistentDataHolder holder, NamespacedKey key, short def) {
        return getShort(holder, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static short getShort(PersistentDataContainer pdc, NamespacedKey key, short def) {
        return getShort(pdc, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static short getShort(PersistentDataHolder holder, NamespacedKey key, short def, boolean setDefaultIfNone) {
        return getShort(holder.getPersistentDataContainer(), key, def, setDefaultIfNone);
    }
    @ParametersAreNonnullByDefault
    public static short getShort(PersistentDataContainer pdc, NamespacedKey key, short def, boolean setDefaultIfNone) {
        if (pdc.has(key, PersistentDataType.SHORT)) {
            return pdc.get(key, PersistentDataType.SHORT);
        } else if (setDefaultIfNone) {
            pdc.set(key, PersistentDataType.SHORT, def);
        }

        return def;
    }
    
    @ParametersAreNonnullByDefault
    public static int getInt(PersistentDataHolder holder, NamespacedKey key, int def) {
        return getInt(holder, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static int getInt(PersistentDataContainer pdc, NamespacedKey key, int def) {
        return getInt(pdc, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static int getInt(PersistentDataHolder holder, NamespacedKey key, int def, boolean setDefaultIfNone) {
        return getInt(holder.getPersistentDataContainer(), key, def, setDefaultIfNone);
    }
    @ParametersAreNonnullByDefault
    public static int getInt(PersistentDataContainer pdc, NamespacedKey key, int def, boolean setDefaultIfNone) {
        if (pdc.has(key, PersistentDataType.INTEGER)) {
            return pdc.get(key, PersistentDataType.INTEGER);
        } else if (setDefaultIfNone) {
            pdc.set(key, PersistentDataType.INTEGER, def);
        }

        return def;
    }
    
    @ParametersAreNonnullByDefault
    public static long getLong(PersistentDataHolder holder, NamespacedKey key, long def) {
        return getLong(holder, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static long getLong(PersistentDataContainer pdc, NamespacedKey key, long def) {
        return getLong(pdc, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static long getLong(PersistentDataHolder holder, NamespacedKey key, long def, boolean setDefaultIfNone) {
        return getLong(holder.getPersistentDataContainer(), key, def, setDefaultIfNone);
    }
    @ParametersAreNonnullByDefault
    public static long getLong(PersistentDataContainer pdc, NamespacedKey key, long def, boolean setDefaultIfNone) {
        if (pdc.has(key, PersistentDataType.LONG)) {
            return pdc.get(key, PersistentDataType.LONG);
        } else if (setDefaultIfNone) {
            pdc.set(key, PersistentDataType.LONG, def);
        }

        return def;
    }
    
    @ParametersAreNonnullByDefault
    public static float getFloat(PersistentDataHolder holder, NamespacedKey key, float def) {
        return getFloat(holder, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static float getFloat(PersistentDataContainer pdc, NamespacedKey key, float def) {
        return getFloat(pdc, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static float getFloat(PersistentDataHolder holder, NamespacedKey key, float def, boolean setDefaultIfNone) {
        return getFloat(holder.getPersistentDataContainer(), key, def, setDefaultIfNone);
    }
    @ParametersAreNonnullByDefault
    public static float getFloat(PersistentDataContainer pdc, NamespacedKey key, float def, boolean setDefaultIfNone) {
        if (pdc.has(key, PersistentDataType.FLOAT)) {
            return pdc.get(key, PersistentDataType.FLOAT);
        } else if (setDefaultIfNone) {
            pdc.set(key, PersistentDataType.FLOAT, def);
        }

        return def;
    }
    
    @ParametersAreNonnullByDefault
    public static double getDouble(PersistentDataHolder holder, NamespacedKey key, double def) {
        return getDouble(holder, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static double getDouble(PersistentDataContainer pdc, NamespacedKey key, double def) {
        return getDouble(pdc, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static double getDouble(PersistentDataHolder holder, NamespacedKey key, double def, boolean setDefaultIfNone) {
        return getDouble(holder.getPersistentDataContainer(), key, def, setDefaultIfNone);
    }
    @ParametersAreNonnullByDefault
    public static double getDouble(PersistentDataContainer pdc, NamespacedKey key, double def, boolean setDefaultIfNone) {
        if (pdc.has(key, PersistentDataType.DOUBLE)) {
            return pdc.get(key, PersistentDataType.DOUBLE);
        } else if (setDefaultIfNone) {
            pdc.set(key, PersistentDataType.DOUBLE, def);
        }

        return def;
    }
    
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String getString(PersistentDataHolder holder, NamespacedKey key, String def) {
        return getString(holder, key, def, true);
    }
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String getString(PersistentDataContainer pdc, NamespacedKey key, String def) {
        return getString(pdc, key, def, true);
    }
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String getString(PersistentDataHolder holder, NamespacedKey key, String def, boolean setDefaultIfNone) {
        return getString(holder.getPersistentDataContainer(), key, def, setDefaultIfNone);
    }
    @Nonnull
    @ParametersAreNonnullByDefault
    public static String getString(PersistentDataContainer pdc, NamespacedKey key, String def, boolean setDefaultIfNone) {
        if (pdc.has(key, PersistentDataType.STRING)) {
            return pdc.get(key, PersistentDataType.STRING);
        } else if (setDefaultIfNone) {
            pdc.set(key, PersistentDataType.STRING, def);
        }

        return def;
    }
    
    @ParametersAreNonnullByDefault
    public static boolean getBool(PersistentDataHolder holder, NamespacedKey key, boolean def) {
        return getBool(holder, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static boolean getBool(PersistentDataContainer pdc, NamespacedKey key, boolean def) {
        return getBool(pdc, key, def, true);
    }
    @ParametersAreNonnullByDefault
    public static boolean getBool(PersistentDataHolder holder, NamespacedKey key, boolean def, boolean setDefaultIfNone) {
        return getBool(holder.getPersistentDataContainer(), key, def, setDefaultIfNone);
    }
    @ParametersAreNonnullByDefault
    public static boolean getBool(PersistentDataContainer pdc, NamespacedKey key, boolean def, boolean setDefaultIfNone) {
        if (pdc.has(key, PersistentDataType.BOOLEAN)) {
            return pdc.get(key, PersistentDataType.BOOLEAN);
        } else if (setDefaultIfNone) {
            pdc.set(key, PersistentDataType.BOOLEAN, def);
        }

        return def;
    }

    @ParametersAreNonnullByDefault
    public static void setByte(PersistentDataHolder holder, NamespacedKey key, byte value) {
        setByte(holder.getPersistentDataContainer(), key, value);
    }
    @ParametersAreNonnullByDefault
    public static void setByte(PersistentDataContainer pdc, NamespacedKey key, byte value) {
        pdc.set(key, PersistentDataType.BYTE, value);
    }

    @ParametersAreNonnullByDefault
    public static void setShort(PersistentDataHolder holder, NamespacedKey key, short value) {
        setShort(holder.getPersistentDataContainer(), key, value);
    }
    @ParametersAreNonnullByDefault
    public static void setShort(PersistentDataContainer pdc, NamespacedKey key, short value) {
        pdc.set(key, PersistentDataType.SHORT, value);
    }

    @ParametersAreNonnullByDefault
    public static void setInt(PersistentDataHolder holder, NamespacedKey key, int value) {
        setInt(holder.getPersistentDataContainer(), key, value);
    }
    @ParametersAreNonnullByDefault
    public static void setInt(PersistentDataContainer pdc, NamespacedKey key, int value) {
        pdc.set(key, PersistentDataType.INTEGER, value);
    }

    @ParametersAreNonnullByDefault
    public static void setLong(PersistentDataHolder holder, NamespacedKey key, long value) {
        setLong(holder.getPersistentDataContainer(), key, value);
    }
    @ParametersAreNonnullByDefault
    public static void setLong(PersistentDataContainer pdc, NamespacedKey key, long value) {
        pdc.set(key, PersistentDataType.LONG, value);
    }

    @ParametersAreNonnullByDefault
    public static void setFloat(PersistentDataHolder holder, NamespacedKey key, float value) {
        setFloat(holder.getPersistentDataContainer(), key, value);
    }
    @ParametersAreNonnullByDefault
    public static void setFloat(PersistentDataContainer pdc, NamespacedKey key, float value) {
        pdc.set(key, PersistentDataType.FLOAT, value);
    }

    @ParametersAreNonnullByDefault
    public static void setDouble(PersistentDataHolder holder, NamespacedKey key, double value) {
        setDouble(holder.getPersistentDataContainer(), key, value);
    }
    @ParametersAreNonnullByDefault
    public static void setDouble(PersistentDataContainer pdc, NamespacedKey key, double value) {
        pdc.set(key, PersistentDataType.DOUBLE, value);
    }

    @ParametersAreNonnullByDefault
    public static void setString(PersistentDataHolder holder, NamespacedKey key, String value) {
        setString(holder.getPersistentDataContainer(), key, value);
    }
    @ParametersAreNonnullByDefault
    public static void setString(PersistentDataContainer pdc, NamespacedKey key, String value) {
        pdc.set(key, PersistentDataType.STRING, value);
    }

    @ParametersAreNonnullByDefault
    public static void setBool(PersistentDataHolder holder, NamespacedKey key, boolean value) {
        setBool(holder.getPersistentDataContainer(), key, value);
    }
    @ParametersAreNonnullByDefault
    public static void setBool(PersistentDataContainer pdc, NamespacedKey key, boolean value) {
        pdc.set(key, PersistentDataType.BOOLEAN, value);
    }

    @ParametersAreNonnullByDefault
    public static void toggle(PersistentDataContainer pdc, NamespacedKey key) {
        final Boolean oldValue = pdc.get(key, PersistentDataType.BOOLEAN);
        pdc.set(key, PersistentDataType.BOOLEAN, oldValue == null ? true : !oldValue);
    }
}
