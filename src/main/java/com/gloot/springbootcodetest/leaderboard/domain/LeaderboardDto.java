package com.gloot.springbootcodetest.leaderboard.domain;

import lombok.Builder;
import lombok.Value;

import java.util.List;
import java.util.UUID;

@Value
@Builder
public class LeaderboardDto {
    UUID id;
    String gameName;
    List<LeaderboardEntryDto> players;
}
