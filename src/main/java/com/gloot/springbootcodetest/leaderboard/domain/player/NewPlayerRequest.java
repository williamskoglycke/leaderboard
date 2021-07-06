package com.gloot.springbootcodetest.leaderboard.domain.player;

import lombok.EqualsAndHashCode;
import lombok.Value;

@Value
@EqualsAndHashCode(callSuper = true)
public class NewPlayerRequest extends PlayerBase {
}
