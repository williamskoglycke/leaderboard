package com.gloot.springbootcodetest.leaderboard.infrastructure.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;
import java.util.UUID;

@Entity(name = "players")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID playerId;

    private String nick;

    @OneToMany(mappedBy = "player")
    private Set<LeaderboardPlayerEntity> leaderboardPlayerEntities;

    public UUID getPlayerId() {
        return playerId;
    }

    public void setPlayerId(UUID playerId) {
        this.playerId = playerId;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public Set<LeaderboardPlayerEntity> getLeaderboardPlayerEntities() {
        return leaderboardPlayerEntities;
    }

    public void setLeaderboardPlayerEntities(Set<LeaderboardPlayerEntity> leaderboardPlayerEntities) {
        this.leaderboardPlayerEntities = leaderboardPlayerEntities;
    }
}
