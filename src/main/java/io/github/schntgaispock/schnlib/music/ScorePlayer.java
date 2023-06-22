package io.github.schntgaispock.schnlib.music;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import io.github.schntgaispock.schnlib.SchnLib;
import lombok.Getter;

@Getter 
public class ScorePlayer extends BukkitRunnable {

    public static final Map<UUID, ScorePlayer> listeners = new HashMap<>();

    private final Score score;
    private final Player player;
    private int beat = 0;

    public ScorePlayer(Score score, Player player) {
        this.score = score;
        this.player = player;
    }

    @Override
    public void run() {
        if (beat > score.getLength()) {
            if (score.isRepeating()) {
                beat = score.getRepeatAt();
            } else {
                cancel();
            }
        }
        score.play(player, beat++);
    }

    public static void play(Player player, Score score, boolean overrideExisting) {
        if (SchnLib.getAddon() == null) {
            return;
        }

        if (score.getLength() <= 0) {
            SchnLib.error("Cannot play score with length <= 0!");
            return;
        }

        final ScorePlayer existing = listeners.get(player.getUniqueId());
        if (overrideExisting || existing == null || existing.isCancelled()) {
            final ScorePlayer newPlayer = new ScorePlayer(score, player);
            listeners.put(player.getUniqueId(), newPlayer);
            newPlayer.runTaskTimer(SchnLib.getAddon(), 0, score.getTicksPerBeat());
        }
    }

    public static void play(Player player, Score score) {
        play(player, score, true);
    }
    
}
