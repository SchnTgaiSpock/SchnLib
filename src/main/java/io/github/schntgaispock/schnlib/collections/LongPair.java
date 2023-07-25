package io.github.schntgaispock.schnlib.collections;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class LongPair {

    private long first;
    private long second;

    public long first() { return first; }
    public void first(long first) { this.first = first; }
    public long second() { return second; }
    public void second(long second) { this.second = second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static LongPair of(long first, long second) {
        return new LongPair(first, second);
    }
    
}
