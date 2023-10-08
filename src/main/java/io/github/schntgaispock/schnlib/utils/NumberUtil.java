package io.github.schntgaispock.schnlib.utils;

import java.util.concurrent.ThreadLocalRandom;

import lombok.Getter;
import lombok.experimental.UtilityClass;

@UtilityClass
public class NumberUtil {

    /**
     * The random number generator SchnLib uses
     */
    private static @Getter ThreadLocalRandom random = ThreadLocalRandom.current();

    /**
     * Clamps a number between a lower bound and an upper bound
     * 
     * @param lowerBound The lower bound
     * @param x          The number to clamp
     * @param upperBound The upper bound
     * @return The number clamped between the lower and upper bounds
     */
    public static int clamp(int lowerBound, int x, int upperBound) {
        return Math.min(Math.max(x, lowerBound), upperBound);
    }

    /**
     * Clamps a number between a lower bound and an upper bound
     * 
     * @param lowerBound The lower bound
     * @param x          The number to clamp
     * @param upperBound The upper bound
     * @return The number clamped between the lower and upper bounds
     */
    public static double clamp(double lowerBound, double x, double upperBound) {
        return Math.min(Math.max(x, lowerBound), upperBound);
    }

    /**
     * Converts a number to its roman numeral. If the number is 0 or >= 4000,
     * doesn't perform any conversion. If negative, prepends a "-".
     * 
     * @param x The number to convert
     * @return <code>x</code> as a roman numeral.
     */
    public static String asRomanNumeral(int x) {
        if (x >= 4000 || x == 0)
            return Integer.toString(x);
        if (x < 0)
            return "-" + asRomanNumeral(-x);
        String[] thousands = { "", "M", "MM", "MMM" };
        String[] hundreds = { "", "C", "CC", "CCC", "CD", "D", "DC", "DCC", "DCCC", "CM" };
        String[] tens = { "", "X", "XX", "XXX", "XL", "L", "LX", "LXX", "LXXX", "XC" };
        String[] ones = { "", "I", "II", "III", "IV", "V", "VI", "VII", "VIII", "IX" };
        return thousands[x / 1000] + hundreds[(x / 100) % 10] + tens[(x / 10) % 10] + ones[x % 10];
    }

    /**
     * Flips a biased coin with a <code>chance</code> probability to land heads, and
     * returns true if landed heads, otherwise false
     * 
     * @param chance The probability to return true, between 0 and 1
     * @return The outcome of the flip
     */
    public static boolean flip(double chance) {
        return random.nextDouble(1) < chance;
    }

    /**
     * Randomly rounds x up or down, preferring the integer it is closer to
     * 
     * @param x The number to round
     * @return The rounded number
     */
    public static int randomRound(double x) {
        final int f = (int) Math.floor(x);
        final int c = (int) Math.ceil(x);
        if (f == c)
            return f;

        return flip(x - f) ? f : c;
    }

    /**
     * Rounds a number to a specified decimal precision
     * 
     * @param x         The number to round
     * @param precision The decimal precision
     * @return The rounded number
     */
    public static double roundToPrecision(double x, int precision) {
        final double magn = Math.pow(10, precision);
        return Math.round(x * magn) / magn;
    }

    /**
     * Convers a number to a percentage, then rounds it to a specified decimal
     * precision
     * 
     * @param x         The number to round
     * @param precision The decimal precision of the percent
     * @return The rounded percent
     */
    public static double roundToPercent(double x, int precision) {
        return roundToPrecision(x * 100, precision);
    }

}
