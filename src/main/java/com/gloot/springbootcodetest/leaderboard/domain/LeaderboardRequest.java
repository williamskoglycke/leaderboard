package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import lombok.Value;

@Value
public class LeaderboardRequest {

    String gameName;

    public LeaderboardRequest(String gameName) {
        if (gameName == null || gameName.isBlank()) {
            throw new ValidationException("gameName is required");
        }
        this.gameName = gameName;
    }
}
