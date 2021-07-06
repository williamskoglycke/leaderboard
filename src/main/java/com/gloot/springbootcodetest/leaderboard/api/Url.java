package com.gloot.springbootcodetest.leaderboard.api;

public final class Url {

    public static final String BASE_URL = "/api";
    public static final String API_VERSION_1 = BASE_URL + "/v1";
    public static final String API_VERSION_2 = BASE_URL + "/v2";

    private Url() {
        throw new RuntimeException("Not even with reflection :)");
    }
}
