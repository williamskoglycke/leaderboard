package com.gloot.springbootcodetest.leaderboard.domain.leaderboard;

import com.gloot.springbootcodetest.leaderboard.domain.player.Player;
import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class Leaderboard extends LeaderboardBase {
    UUID id;
    List<Player> players;
}
