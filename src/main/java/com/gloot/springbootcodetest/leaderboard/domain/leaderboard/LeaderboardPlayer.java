package com.gloot.springbootcodetest.leaderboard.domain.leaderboard;

import com.gloot.springbootcodetest.leaderboard.domain.player.PlayerBase;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class LeaderboardPlayer extends PlayerBase {
    private final UUID id;
    private final Integer score;
    private final Integer position;

    public LeaderboardPlayer(String nick, UUID id, Integer score, Integer position) {
        super(nick);
        this.id = id;
        this.score = score;
        this.position = position;
    }
}
