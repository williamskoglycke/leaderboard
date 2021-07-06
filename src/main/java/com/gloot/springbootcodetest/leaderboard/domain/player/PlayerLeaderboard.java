package com.gloot.springbootcodetest.leaderboard.domain.player;

import com.gloot.springbootcodetest.leaderboard.domain.leaderboard.LeaderboardBase;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class PlayerLeaderboard extends LeaderboardBase {
    UUID id;
    Integer score;
}
