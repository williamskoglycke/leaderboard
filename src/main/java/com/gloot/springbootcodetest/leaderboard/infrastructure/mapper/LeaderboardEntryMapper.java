package com.gloot.springbootcodetest.leaderboard.infrastructure.mapper;

import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;

public class LeaderboardEntryMapper {

  public static LeaderboardEntryDto mapToDto(int pos, LeaderboardEntryEntity entity) {
    return LeaderboardEntryDto.builder()
        .position(pos)
        .nick(entity.getNick())
        .score(entity.getScore())
        .build();
  }
}
