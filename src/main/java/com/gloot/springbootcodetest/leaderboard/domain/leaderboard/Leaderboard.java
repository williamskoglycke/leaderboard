package com.gloot.springbootcodetest.leaderboard.domain.leaderboard;

import com.gloot.springbootcodetest.leaderboard.domain.player.Player;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Leaderboard extends LeaderboardBase {
    private final UUID id;
    private final List<Player> players;

    public Leaderboard(String gameName, UUID id, List<Player> players) {
        super(gameName);
        this.id = id;
        this.players = players;
    }
}
