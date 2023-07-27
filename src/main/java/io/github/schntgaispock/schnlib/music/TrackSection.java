package io.github.schntgaispock.schnlib.music;

import java.util.ArrayList;
import java.util.List;

import io.github.schntgaispock.schnlib.collections.Pair;
import lombok.Getter;

public class TrackSection {

    private final @Getter List<Pair<Integer, Chord>> chords = new ArrayList<>();

    public TrackSection add(int duration, Chord chord) {
        chords.add(Pair.of(duration, chord));

        return this;
    }

    public TrackSection add(float volume, int duration, NotePitch... pitches) {
        return add(duration, new Chord(volume, pitches));
    }

    public TrackSection add(int duration, NotePitch... pitches) {
        return add(1, duration, pitches);
    }

    public TrackSection add(float volume, int duration, float... pitches) {
        return add(duration, new Chord(volume, pitches));
    }

    public TrackSection add(int duration, float... pitches) {
        return add(1, duration, pitches);
    }

}
