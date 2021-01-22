package com.gloot.springbootcodetest.leaderboard;

public class LeaderboardEntryMapper {

  public static LeaderboardEntryDto mapToDto(int pos, LeaderboardEntryEntity entity) {
    return LeaderboardEntryDto.builder()
        .position(pos)
        .nick(entity.getNick())
        .score(entity.getScore())
        .build();
  }
}
