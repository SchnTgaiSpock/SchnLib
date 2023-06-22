package io.github.schntgaispock.schnlib.music;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.bukkit.entity.Player;

import lombok.Getter;

@Getter
public class Score {

    private final List<Track> tracks = new ArrayList<>();
    private final int ticksPerBeat;
    private final boolean repeating;
    private final int repeatAt;
    private int length = 0;

    public Score(int ticksPerBeat, boolean repeating, int repeatAt) {
        this.ticksPerBeat = ticksPerBeat;
        this.repeating = repeating;
        this.repeatAt = repeatAt;
    }

    public Score(int ticksPerBeat, boolean repeating) {
        this(ticksPerBeat, repeating, 0);
    }

    public Score(int ticksPerBeat, int repeatAt) {
        this(ticksPerBeat, true, repeatAt);
    }

    public Score(int ticksPerBeat) {
        this(ticksPerBeat, false);
    }

    public List<Track> getTracks() {
        return Collections.unmodifiableList(tracks);
    }

    public Score addTrack(Track track) {
        tracks.add(track);
        if (track.getLength() > length) {
            length = track.getLength();
        }

        return this;
    }

    public void play(Player player, int beat) {
        for (Track track : tracks) {
            track.play(player, beat);
        }
    }
    
}
