package com.gloot.springbootcodetest.leaderboard.errors;

public class ValidationException extends RuntimeException {

    public ValidationException() {
    }

    public ValidationException(String message) {
        super(message);
    }
}
