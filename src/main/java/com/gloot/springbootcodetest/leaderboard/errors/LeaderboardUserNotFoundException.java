package com.gloot.springbootcodetest.leaderboard.errors;

public class LeaderboardUserNotFoundException extends RuntimeException {

    public LeaderboardUserNotFoundException() {
    }

    public LeaderboardUserNotFoundException(String message) {
        super(message);
    }
}
