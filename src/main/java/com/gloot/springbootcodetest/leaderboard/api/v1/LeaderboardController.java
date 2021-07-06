package com.gloot.springbootcodetest.leaderboard.api.v1;

import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.gloot.springbootcodetest.leaderboard.api.Url.API_VERSION_1;

@RestController
@RequestMapping(API_VERSION_1 + "/leaderboard")
@AllArgsConstructor
public class LeaderboardController {
    private final LeaderboardService service;

    @GetMapping
    public List<LeaderboardEntryDto> getLeaderboard() {
        return service.getListOfAllLeaderboardEntriesAsDTO();
    }
}
