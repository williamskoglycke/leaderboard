package com.gloot.springbootcodetest.leaderboard.infrastructure.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.UUID;

@Data
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

}
