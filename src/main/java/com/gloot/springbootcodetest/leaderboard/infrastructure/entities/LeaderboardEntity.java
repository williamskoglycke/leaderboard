package com.gloot.springbootcodetest.leaderboard.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

import static javax.persistence.FetchType.EAGER;

@Entity(name = "leaderboards")
public class LeaderboardEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID leaderboardId;

    private String gameName;

    @OneToMany(mappedBy = "leaderboard", fetch = EAGER)
    private Set<LeaderboardPlayerEntity> leaderboardPlayerEntities;

    public UUID getLeaderboardId() {
        return leaderboardId;
    }

    public void setLeaderboardId(UUID leaderboardId) {
        this.leaderboardId = leaderboardId;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public Set<LeaderboardPlayerEntity> getLeaderboardPlayerEntities() {
        return leaderboardPlayerEntities;
    }

    public void setLeaderboardPlayerEntities(Set<LeaderboardPlayerEntity> leaderboardPlayerEntities) {
        this.leaderboardPlayerEntities = leaderboardPlayerEntities;
    }
}
