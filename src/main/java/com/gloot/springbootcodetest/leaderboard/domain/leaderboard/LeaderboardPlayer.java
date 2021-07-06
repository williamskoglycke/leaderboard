package com.gloot.springbootcodetest.leaderboard.domain.leaderboard;

import com.gloot.springbootcodetest.leaderboard.domain.player.PlayerBase;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class LeaderboardPlayer extends PlayerBase {
    UUID id;
    Integer score;
    Integer position;
}
