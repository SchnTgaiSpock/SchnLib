package io.github.schntgaispock.schnlib.music;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.schntgaispock.schnlib.SchnLib;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Holds all the information about a single piece.
 */
@Getter
@RequiredArgsConstructor
public class Score {

    private static final @Getter Map<UUID, Score> listeners = new HashMap<>();

    private final String title;
    private final String author;
    private final String arranger;
    private final List<Track> tracks = new ArrayList<>();
    private final List<Integer> durations = new ArrayList<>();
    private final int ticksPerBeat;
    private final int repeatStart;
    private final int repeatEnd;
    private final int repeatFor;
    private static final BukkitRunnable onTick = new BukkitRunnable() {

        private int beat = 0;

        public void run() {
            for (Map.Entry<UUID, Score> entry : listeners.entrySet()) {
                final Player player = Bukkit.getPlayer(entry.getKey());
                if (player == null) {
                    listeners.remove(entry.getKey());
                    return;
                }

                final Score score = entry.getValue();
                for (Track track : score.tracks) {
                    track.play(beat++, player.getLocation());
                }
            }
        };

    };

    public Score(String title, String author, String arranger, int ticksPerBeat, int repeatStart) {
        this(title, author, arranger, ticksPerBeat, repeatStart, -1, -1);
    }

    public Score(String title, String author, int ticksPerBeat, int repeatStart) {
        this(title, author, null, ticksPerBeat, repeatStart, -1, -1);
    }

    public Track track(Track track) {
        tracks.add(track);
        return track;
    }

    public Track track(float volume, Sound instrument) {
        final Track track = new Track(volume, instrument);
        return track(track);
    }

    public Track track(Sound instrument) {
        return track(1, instrument);
    }

    public Track track(int trackNum) {
        return tracks.get(trackNum);
    }

    public void play(Player player) {
        final UUID uuid = player.getUniqueId();
        listeners.put(uuid, this);
    }

    public static void stop(Player player) {
        listeners.remove(player.getUniqueId());
    }

    public static void startPlayer() {
        Score.onTick.runTaskTimer(SchnLib.getAddon(), 0, 1);
    }

}
