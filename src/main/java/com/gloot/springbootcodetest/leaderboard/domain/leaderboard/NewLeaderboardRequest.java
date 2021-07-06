package com.gloot.springbootcodetest.leaderboard.domain.leaderboard;

import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
public class NewLeaderboardRequest extends LeaderboardBase {

    public NewLeaderboardRequest(String gameName) {
        super(gameName);
    }
}
