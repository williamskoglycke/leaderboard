package com.gloot.springbootcodetest.leaderboard.errors;

public class LeaderboardPlayerNotFoundException extends RuntimeException {

    public LeaderboardPlayerNotFoundException() {
    }

    public LeaderboardPlayerNotFoundException(String message) {
        super(message);
    }
}
