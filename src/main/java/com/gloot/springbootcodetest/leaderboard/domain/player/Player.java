package com.gloot.springbootcodetest.leaderboard.domain.player;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.util.Set;
import java.util.UUID;

@Getter
@ToString
@EqualsAndHashCode(callSuper = true)
public class Player extends PlayerBase {

    private final UUID id;
    private final Set<PlayerLeaderboard> leaderboards;

    public Player(String nick, UUID id, Set<PlayerLeaderboard> leaderboards) {
        super(nick);
        this.id = id;
        this.leaderboards = leaderboards;
    }
}
