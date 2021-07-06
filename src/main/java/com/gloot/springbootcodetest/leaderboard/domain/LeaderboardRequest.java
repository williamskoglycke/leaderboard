package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import lombok.Value;

@Value
public class LeaderboardRequest {

    String gameName;

    public LeaderboardRequest(String gameName) {
        this.gameName = validate(gameName);
    }

    private String validate(String gameName) {
        if (gameName == null || gameName.isBlank()) {
            throw new ValidationException("gameName is required");
        }
        if (gameName.length() > 50) {
            throw new ValidationException("gameName cannot exceed 50 characters");
        }
        return gameName;
    }
}
