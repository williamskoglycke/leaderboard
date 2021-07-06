package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import lombok.Value;

@Value
public class PlayerRequest {

    String nick;

    public PlayerRequest(String nick) {
        this.nick = validate(nick);
    }

    private static String validate(String nick) {
        if (nick == null || nick.isBlank()) {
            throw new ValidationException("nick is required");
        }
        if (nick.length() > 20) {
            throw new ValidationException("nick cannot exceed 20 characters");
        }
        return nick;
    }

}
