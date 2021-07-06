package com.gloot.springbootcodetest.leaderboard.domain;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@ToString
@EqualsAndHashCode(callSuper = true)
@JsonIgnoreProperties(ignoreUnknown = true)
public final class NewLeaderboardRequest extends LeaderboardBase {

    @JsonCreator
    public NewLeaderboardRequest(@JsonProperty("gameName") String gameName) {
        super(gameName);
    }
}
