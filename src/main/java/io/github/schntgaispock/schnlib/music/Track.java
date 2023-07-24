package io.github.schntgaispock.schnlib.music;

import java.util.ArrayList;
import java.util.List;

import io.github.schntgaispock.schnlib.SchnLib;
import io.github.schntgaispock.schnlib.SchnLib.Options;
import lombok.Getter;

public class Track {

    private final @Getter List<Chord> chords = new ArrayList<>();
    private @Getter int duration = 0;
    private final @Getter int ticksPerBeat;
    private final @Getter float volume;

    public Track(int ticksPerBeat, float volume) {
        this.ticksPerBeat = ticksPerBeat;
        this.volume = volume;
    }

    public Track add(Chord... chords) {
        for (Chord chord : chords) {
            this.chords.add(chord);
            duration += chord.getDuration();
        }

        return this;
    }

    public Track add(TrackSection section, int repeat) {
        if (!section.finalized && !SchnLib.getOptions().contains(Options.IGNORE_UNFINALIZED_TRACK_SECTIONS)) {
            SchnLib.warn("TrackSection is not finalized! Changes in notes will not be reflected in this track");
        }

        for (int i = 0; i < repeat; i++) {
            for (Chord chord : section.getChords()) {
                this.chords.add(chord);
            }
            duration += section.getDuration();
        }

        return this;
    }

    public Track add(TrackSection section) {
        return add(section, 1);
    }

}
