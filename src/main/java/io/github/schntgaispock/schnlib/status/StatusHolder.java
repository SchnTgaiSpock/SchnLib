package io.github.schntgaispock.schnlib.status;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class StatusHolder<T> {

    private final @Getter T holder;

    @Override
    public int hashCode() {
        return holder.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return (obj instanceof final StatusHolder<?> other) && other.getHolder().equals(this.getHolder());
    }

}
