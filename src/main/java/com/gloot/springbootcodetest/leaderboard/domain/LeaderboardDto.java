package com.gloot.springbootcodetest.leaderboard.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.List;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class LeaderboardDto extends LeaderboardBase {
    private final UUID id;
    private final List<PlayerDto> players;

    public LeaderboardDto(String gameName, UUID id, List<PlayerDto> players) {
        super(gameName);
        this.id = id;
        this.players = players;
    }
}
