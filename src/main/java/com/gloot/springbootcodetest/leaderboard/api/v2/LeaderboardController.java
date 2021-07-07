package com.gloot.springbootcodetest.leaderboard.api.v2;

import com.gloot.springbootcodetest.leaderboard.api.ErrorResponse;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardService;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardDto;
import com.gloot.springbootcodetest.leaderboard.domain.PlayerDto;
import com.gloot.springbootcodetest.leaderboard.domain.NewLeaderboardRequest;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardOrPlayerNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardPlayerNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.PlayerAlreadyInLeaderboardException;
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
import java.util.Map;
import java.util.Optional;
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
    public List<LeaderboardDto> getAllLeaderboards() {
        return leaderboardService.getAllLeaderboards();
    }

    @PostMapping
    public ResponseEntity<Map<String, UUID>> addLeaderboard(@RequestBody NewLeaderboardRequest request) {
        // TODO: Fix Springs default "body missing" error to a nicer one.
        UUID leaderboardId = leaderboardService.addLeaderboard(request);
        return ResponseEntity
                .created(URI.create(API_VERSION_2 + "/leaderboards/" + leaderboardId))
                .body(Map.of("leaderboardId", leaderboardId));
    }

    @GetMapping("/{leaderboardId}")
    public LeaderboardDto getLeaderboardById(@PathVariable UUID leaderboardId) {
        return leaderboardService.getLeaderboardById(leaderboardId);
    }

    @GetMapping("/{leaderboardId}/players")
    public List<PlayerDto> getPlayersByLeaderboard(@PathVariable UUID leaderboardId) {
        return leaderboardService.getLeaderboardById(leaderboardId).getPlayers();
    }

    @GetMapping("/{leaderboardId}/players/{playerId}")
    public PlayerDto getLeaderboardPlayerById(@PathVariable UUID leaderboardId,
                                              @PathVariable UUID playerId) {
        return leaderboardService.getLeaderboardPlayerById(leaderboardId, playerId);
    }

    @PostMapping("/{leaderboardId}/players/{playerId}")
    public ResponseEntity<Object> addPlayerToLeaderboard(@PathVariable UUID leaderboardId,
                                                         @PathVariable UUID playerId,
                                                         @RequestParam(name = "score", required = false) Optional<Integer> newScore) {
        leaderboardService.addPlayerToLeaderboard(leaderboardId, playerId, newScore);
        URI location = URI.create(API_VERSION_2 + "/leaderboards/" + leaderboardId + "/player/" + playerId);
        return ResponseEntity.created(location).build();
    }

    @PutMapping("/{leaderboardId}/players/{playerId}")
    public void updateUserScore(@PathVariable UUID leaderboardId,
                                @PathVariable UUID playerId,
                                @RequestParam("score") Integer newScore) {
        // TODO: Fix Springs default "param missing" error to a nicer one.
        leaderboardService.updatePlayerScore(leaderboardId, playerId, newScore);
    }

    @GetMapping("/{leaderboardId}/players/{playerId}/position")
    public Map<String, Integer> getLeaderboardUserPosition(@PathVariable UUID leaderboardId,
                                                           @PathVariable UUID playerId) {
        Integer position = leaderboardService.getLeaderboardPlayerPosition(leaderboardId, playerId);
        return Map.of("position", position);
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

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(LeaderboardOrPlayerNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLeaderboardOrPlayerNotFoundException(LeaderboardOrPlayerNotFoundException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(badRequest(4003, e.getMessage()));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(PlayerAlreadyInLeaderboardException.class)
    public ResponseEntity<ErrorResponse> handlePlayerAlreadyInLeaderboardException(PlayerAlreadyInLeaderboardException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(badRequest(4004, e.getMessage()));
    }
}
