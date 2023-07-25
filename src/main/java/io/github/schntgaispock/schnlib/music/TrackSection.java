package io.github.schntgaispock.schnlib.music;

import java.util.ArrayList;
import java.util.List;

import io.github.schntgaispock.schnlib.SchnLib;
import io.github.schntgaispock.schnlib.collections.Pair;
import lombok.Getter;

public class TrackSection {

    private final @Getter List<Pair<Integer, Chord>> chords = new ArrayList<>();

    boolean finalized = false;

    public TrackSection add(int duration, Chord chord) {
        if (finalized) {
            SchnLib.error("Cannot add chords because this TrackSection is already finalized!");
            return this;
        }

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

    public void finalize() {
        finalized = true;
    }

}
