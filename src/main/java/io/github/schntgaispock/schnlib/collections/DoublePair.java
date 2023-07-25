package io.github.schntgaispock.schnlib.collections;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DoublePair {

    private double first;
    private double second;

    public double first() { return first; }
    public void first(double first) { this.first = first; }
    public double second() { return second; }
    public void second(double second) { this.second = second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static DoublePair of(double first, double second) {
        return new DoublePair(first, second);
    }
    
}
