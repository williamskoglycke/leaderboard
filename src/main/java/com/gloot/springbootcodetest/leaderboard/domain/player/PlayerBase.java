package com.gloot.springbootcodetest.leaderboard.domain.player;

import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
@EqualsAndHashCode
public abstract class PlayerBase {
    private final String nick;

    public PlayerBase(String nick) {
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
