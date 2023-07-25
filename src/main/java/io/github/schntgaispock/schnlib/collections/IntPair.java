package io.github.schntgaispock.schnlib.collections;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class IntPair {

    private int first;
    private int second;

    public int first() { return first; }
    public void first(int first) { this.first = first; }
    public int second() { return second; }
    public void second(int second) { this.second = second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static IntPair of(int first, int second) {
        return new IntPair(first, second);
    }
    
}
