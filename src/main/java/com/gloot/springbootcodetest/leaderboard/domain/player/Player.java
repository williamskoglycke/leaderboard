package com.gloot.springbootcodetest.leaderboard.domain.player;

import lombok.EqualsAndHashCode;
import lombok.Value;

import java.util.Set;
import java.util.UUID;

@Value
@EqualsAndHashCode(callSuper = true)
public class Player extends PlayerBase {

    UUID id;
    Set<PlayerLeaderboard> leaderboards;

}
