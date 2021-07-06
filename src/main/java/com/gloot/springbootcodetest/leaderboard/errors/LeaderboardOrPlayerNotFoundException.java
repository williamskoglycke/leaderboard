package com.gloot.springbootcodetest.leaderboard.errors;

public class LeaderboardOrPlayerNotFoundException extends RuntimeException {

    public LeaderboardOrPlayerNotFoundException() {
    }

    public LeaderboardOrPlayerNotFoundException(String message) {
        super(message);
    }
}
