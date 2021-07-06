package com.gloot.springbootcodetest.leaderboard.infrastructure.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Data
@Entity
@Table(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID playerId;

    private String nick;

    @OneToMany(mappedBy = "player", fetch = EAGER)
    private Set<LeaderboardPlayerEntity> leaderboardPlayerEntities;

}
