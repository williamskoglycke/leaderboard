package com.gloot.springbootcodetest.leaderboard.domain.player;

import com.gloot.springbootcodetest.leaderboard.domain.leaderboard.LeaderboardBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class PlayerLeaderboard extends LeaderboardBase {
    private final UUID id;
    private final Integer score;
    private final Integer position;

    public PlayerLeaderboard(String gameName, UUID id, Integer score, Integer position) {
        super(gameName);
        this.id = id;
        this.score = score;
        this.position = position;
    }
}
