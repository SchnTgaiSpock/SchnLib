package io.github.schntgaispock.schnlib;

import lombok.experimental.UtilityClass;

@UtilityClass
public class Timing {

    private long startTime = 0;

    public static void start() {
        startTime = System.nanoTime();
    }

    public static void print(String message) {
        System.out.println(String.format(message, (System.nanoTime() - startTime) / 1000000.));
    }
    
}
