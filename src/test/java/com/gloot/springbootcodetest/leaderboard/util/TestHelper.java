package com.gloot.springbootcodetest.leaderboard.util;

import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardDto;
import com.gloot.springbootcodetest.leaderboard.domain.PlayerDto;

import java.util.List;
import java.util.UUID;

public class TestHelper {

    public static final String TEST_UUID = "9604e4d8-a763-4d43-9def-6704f74bb09b";

    public static LeaderboardDto getLeaderboard() {
        return new LeaderboardDto("DOTA2", UUID.fromString(TEST_UUID), List.of(getPlayer()));
    }

    public static PlayerDto getPlayer() {
        return new PlayerDto("NickFury", UUID.fromString(TEST_UUID), 1337, 1);
    }
}
