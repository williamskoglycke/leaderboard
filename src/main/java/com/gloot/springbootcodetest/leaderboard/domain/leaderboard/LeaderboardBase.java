package com.gloot.springbootcodetest.leaderboard.domain.leaderboard;

import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public abstract class LeaderboardBase {
    private final String gameName;

    public LeaderboardBase(String gameName) {
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
