package com.gloot.springbootcodetest.leaderboard.domain;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class LeaderboardEntryDto {
  int position;
  String nick;
  Integer score;
}
