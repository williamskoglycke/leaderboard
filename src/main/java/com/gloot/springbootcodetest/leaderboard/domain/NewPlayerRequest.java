package com.gloot.springbootcodetest.leaderboard.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import lombok.Value;

@Value
@JsonIgnoreProperties(ignoreUnknown = true)
public class NewPlayerRequest {

    String nick;

    @JsonCreator
    public NewPlayerRequest(@JsonProperty("nick") String nick) {
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
