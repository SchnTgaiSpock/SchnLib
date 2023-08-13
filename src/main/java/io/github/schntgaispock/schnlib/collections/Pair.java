package io.github.schntgaispock.schnlib.collections;


import lombok.AccessLevel;
import lombok.AllArgsConstructor;

@AllArgsConstructor( access = AccessLevel.PROTECTED )
public class Pair<T, U> {

    private T first;
    private U second;

    public T first() { return first; }
    public void first(T first) { this.first = first; }
    public U second() { return second; }
    public void second(U second) { this.second = second; }

    @Override
    public String toString() {
        return "(" + first + ", " + second + ")";
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

}
