package com.gloot.springbootcodetest.leaderboard.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "leaderboard_player")
public class LeaderboardPlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "leaderboard_id")
    private LeaderboardEntity leaderboard;

    @ManyToOne
    @JoinColumn(name = "player_id")
    private PlayerEntity player;

    private Integer score;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public LeaderboardEntity getLeaderboard() {
        return leaderboard;
    }

    public void setLeaderboard(LeaderboardEntity leaderboard) {
        this.leaderboard = leaderboard;
    }

    public PlayerEntity getPlayer() {
        return player;
    }

    public void setPlayer(PlayerEntity player) {
        this.player = player;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}
