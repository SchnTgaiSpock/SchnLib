package io.github.schntgaispock.schnlib;

import java.util.ArrayList;
import java.util.Arrays;

import io.github.schntgaispock.schnlib.utils.CollectionUtil;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CollectionUtilTests {


    @Getter
    @RequiredArgsConstructor
    private class StripTest {
        final Integer[] input;
        final int inputWidth;
        final int inputHeight;
        final Integer[] expected;
    }

    public static void testStrip() {
        final var tests = new ArrayList<StripTest>();

        tests.add(new StripTest(
            new Integer[9], 3, 3, 
            new Integer[0]));
        tests.add(new StripTest(
            new Integer[10000], 100, 100, 
            new Integer[0]));
        tests.add(new StripTest(
            new Integer[1000000], 1000, 1000, 
            new Integer[0]));
        tests.add(new StripTest(
            new Integer[] { null, null, null, null, 1, null, null, null, null }, 3, 3, 
            new Integer[] { 1 }));
        tests.add(new StripTest(
            new Integer[] { null, 1, null, 2, null, 3, null, 4, null }, 3, 3, 
            new Integer[] { null, 1, null, 2, null, 3, null, 4, null }));
        tests.add(new StripTest(
            new Integer[] { 1, 2, 3, null, null, null, null, null, null }, 3, 3, 
            new Integer[] { 1, 2, 3 }));
        tests.add(new StripTest(
            new Integer[] { null, null, null, 1, 2, 3, null, null, null }, 3, 3, 
            new Integer[] { 1, 2, 3 }));
        tests.add(new StripTest(
            new Integer[] { null, null, null, null, null, null, 1, 2, 3 }, 3, 3, 
            new Integer[] { 1, 2, 3 }));
        tests.add(new StripTest(
            new Integer[] { 1, null, null, 2, null, null, 3, null, null }, 3, 3, 
            new Integer[] { 1, 2, 3 }));
        tests.add(new StripTest(
            new Integer[] { null, 1, null, null, 2, null, null, 3, null }, 3, 3, 
            new Integer[] { 1, 2, 3 }));
        tests.add(new StripTest(
            new Integer[] { null, null, 1, null, null, 2, null, null, 3 }, 3, 3, 
            new Integer[] { 1, 2, 3 }));
        tests.add(new StripTest(
            new Integer[] { 1, 2, null, 3, null, null, null, null, null }, 3, 3, 
            new Integer[] { 1, 2, 3, null }));
        tests.add(new StripTest(
            new Integer[] { null, null, null, 1, 2, null, null, null, null }, 3, 3, 
            new Integer[] { 1, 2 }));

        int testNum = 0;
        for (var test : tests) {
            testNum++;
            final var output = CollectionUtil.strip(test.getInput(), test.getInputWidth(), test.getInputHeight());
            final boolean result = Arrays.toString(output.first()).equals(Arrays.toString(test.getExpected()));
            System.out.println("Test " + testNum + ": " + (result ? "PASSED" : "FAILED"));
        }
    }

    public static void testStrip2() {
        System.out.println("Starting testStrip2");
        Timing.start();
        for (long i = 0; i < 10e7; i++) {
            CollectionUtil.strip(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9 }, 3, 3);
        }
        Timing.print("testStrip2 Completed in %sms");
    }

    public static void main(String[] args) {
        testStrip();
        testStrip2();
    }

}