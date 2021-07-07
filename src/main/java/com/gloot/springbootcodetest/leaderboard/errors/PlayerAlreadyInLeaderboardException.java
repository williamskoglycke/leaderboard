package com.gloot.springbootcodetest.leaderboard.errors;

public class PlayerAlreadyInLeaderboardException extends RuntimeException {

    public PlayerAlreadyInLeaderboardException() {
    }

    public PlayerAlreadyInLeaderboardException(String message) {
        super(message);
    }
}
