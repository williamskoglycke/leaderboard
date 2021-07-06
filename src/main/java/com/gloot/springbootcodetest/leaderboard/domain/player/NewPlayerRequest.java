package com.gloot.springbootcodetest.leaderboard.domain.player;

import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
public class NewPlayerRequest extends PlayerBase {
    public NewPlayerRequest(String nick) {
        super(nick);
    }
}
