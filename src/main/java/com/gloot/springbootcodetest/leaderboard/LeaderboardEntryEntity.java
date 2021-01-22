package com.gloot.springbootcodetest.leaderboard;

import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "leaderboard")
@Data
@Builder(toBuilder = true)
@NoArgsConstructor
@AllArgsConstructor
public class LeaderboardEntryEntity {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private UUID uuid;
  private String nick;
  private Integer score;

  public LeaderboardEntryEntity (String nick, Integer score) {
    this.nick = nick;
    this.score = score;
  }
}
