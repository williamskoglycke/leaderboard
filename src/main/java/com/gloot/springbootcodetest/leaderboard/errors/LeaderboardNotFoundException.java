package com.gloot.springbootcodetest.leaderboard.errors;

public class LeaderboardNotFoundException extends RuntimeException {

    public LeaderboardNotFoundException() {
    }

    public LeaderboardNotFoundException(String message) {
        super(message);
    }
}
