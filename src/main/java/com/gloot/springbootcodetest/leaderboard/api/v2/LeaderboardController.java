package com.gloot.springbootcodetest.leaderboard.api.v2;

import com.gloot.springbootcodetest.leaderboard.api.ErrorResponse;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardService;
import com.gloot.springbootcodetest.leaderboard.domain.leaderboard.Leaderboard;
import com.gloot.springbootcodetest.leaderboard.domain.leaderboard.NewLeaderboardRequest;
import com.gloot.springbootcodetest.leaderboard.domain.player.Player;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardPlayerNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.UUID;

import static com.gloot.springbootcodetest.leaderboard.api.ErrorResponse.badRequest;
import static com.gloot.springbootcodetest.leaderboard.api.Url.API_VERSION_2;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController("leaderboardControllerV2")
@RequestMapping(API_VERSION_2 + "/leaderboards")
@AllArgsConstructor
public class LeaderboardController {
    private final LeaderboardService leaderboardService;

    @GetMapping
    public List<Leaderboard> getAllLeaderboards() {
        return leaderboardService.getAllLeaderboards();
    }

    @PostMapping
    public ResponseEntity<?> addLeaderboard(@RequestBody NewLeaderboardRequest request) {
        // TODO: Fix Springs default "body missing" error to a nicer one.
        UUID leaderboardId = leaderboardService.addLeaderboard(request);
        return ResponseEntity.created(URI.create(API_VERSION_2 + "/leaderboards/" + leaderboardId)).build();
    }

    @GetMapping("/{leaderboardId}")
    public Leaderboard getLeaderboardById(@PathVariable String leaderboardId) {
        return leaderboardService.getLeaderboardById(leaderboardId);
    }

    @GetMapping("/{leaderboardId}/players")
    public List<Player> getPlayersByLeaderboard(@PathVariable String leaderboardId) {
        return leaderboardService.getLeaderboardById(leaderboardId).getPlayers();
    }

    @GetMapping("/{leaderboardId}/players/{playerId}")
    public Player getLeaderboardPlayerById(@PathVariable String leaderboardId,
                                           @PathVariable String playerId) {
        return leaderboardService.getLeaderboardPlayerById(leaderboardId, playerId);
    }

    @PostMapping("/{leaderboardId}/players/{playerId}")
    public ResponseEntity<?> addPlayerToLeaderboard(@PathVariable String leaderboardId,
                                                    @PathVariable String playerId) {
        String userId = leaderboardService.addPlayerToLeaderboard(leaderboardId, playerId);
        URI location = URI.create(API_VERSION_2 + "/leaderboards/" + leaderboardId + "/player/" + userId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{leaderboardId}/players/{playerId}")
    public void updateUserScore(@PathVariable String leaderboardId,
                                @PathVariable String playerId,
                                @RequestParam("score") Integer newScore) {
        // TODO: Fix Springs default "param missing" error to a nicer one.
        leaderboardService.updatePlayerScore(leaderboardId, playerId, newScore);
    }

    @GetMapping("/{leaderboardId}/players/{playerId}/position")
    public Integer getLeaderboardUserPosition(@PathVariable String leaderboardId,
                                              @PathVariable String playerId) {
        return leaderboardService.getLeaderboardPlayerPosition(leaderboardId, playerId);
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(LeaderboardNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLeaderboardNotFoundException(LeaderboardNotFoundException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(badRequest(4001, e.getMessage()));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(LeaderboardPlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException(LeaderboardPlayerNotFoundException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(badRequest(4002, e.getMessage()));
    }
}
