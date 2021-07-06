package com.gloot.springbootcodetest.leaderboard.domain;

import lombok.Value;

import java.util.Optional;
import java.util.UUID;

@Value
public class PlayerDto {
    String nick;
    UUID id;
    Integer score;
    Integer position;

    public Optional<Integer> getPosition() {
        return Optional.ofNullable(position);
    }
}
