package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import lombok.Value;

@Value
public class LeaderboardUserRequest {

    String nick;

    public LeaderboardUserRequest(String nick) {
        if (nick == null || nick.isBlank()) {
            throw new ValidationException("nick is required");
        }
        this.nick = nick;
    }

}
