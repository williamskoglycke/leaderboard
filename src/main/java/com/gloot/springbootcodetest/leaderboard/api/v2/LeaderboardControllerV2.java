package com.gloot.springbootcodetest.leaderboard.api.v2;

import com.gloot.springbootcodetest.leaderboard.api.ErrorResponse;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardRequest;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardDto;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardService;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.domain.PlayerRequest;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardUserNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
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

import static com.gloot.springbootcodetest.leaderboard.api.ErrorResponse.badRequest;
import static com.gloot.springbootcodetest.leaderboard.api.Url.API_VERSION_2;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@RequestMapping(API_VERSION_2 + "/leaderboards")
@AllArgsConstructor
public class LeaderboardControllerV2 {
    private final LeaderboardService leaderboardService;

    @GetMapping
    public List<LeaderboardDto> getAllLeaderboards() {
        return leaderboardService.getAllLeaderboards();
    }

    @PostMapping
    public ResponseEntity<?> addLeaderboard(@RequestBody LeaderboardRequest request) {
        // TODO: Fix Springs default "body missing" error to a nicer one.
        String leaderboardId = leaderboardService.addLeaderboard(request);
        return ResponseEntity.created(URI.create(API_VERSION_2 + "/leaderboards/" + leaderboardId)).build();
    }

    @GetMapping("/{leaderboardId}")
    public LeaderboardDto getLeaderboardById(@PathVariable String leaderboardId) {
        return leaderboardService.getLeaderboardById(leaderboardId);
    }

    @GetMapping("/{leaderboardId}/users")
    public List<LeaderboardEntryDto> getAllLeaderboardUsers(@PathVariable String leaderboardId) {
        return leaderboardService.getLeaderboardById(leaderboardId).getPlayers();
    }

    @PostMapping("/{leaderboardId}/users")
    public ResponseEntity<?> addNewUserToLeaderboard(@PathVariable String leaderboardId,
                                                     @RequestBody PlayerRequest playerRequest) {
        String userId = leaderboardService.addNewUserToLeaderboard(leaderboardId, playerRequest);
        URI location = URI.create(API_VERSION_2 + "/leaderboards/" + leaderboardId + "/users/" + userId);
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/{leaderboardId}/users/{userId}")
    public LeaderboardEntryDto getLeaderboardUserById(@PathVariable String leaderboardId,
                                                      @PathVariable String userId) {
        return leaderboardService.getLeaderboardUserById(leaderboardId, userId);
    }

    @PutMapping("/{leaderboardId}/users/{userId}")
    public void updateUserScore(@PathVariable String leaderboardId,
                                @PathVariable String userId,
                                @RequestParam("score") Integer newScore) {
        // TODO: Fix Springs default "param missing" error to a nicer one.
        leaderboardService.updateUserScore(leaderboardId, userId, newScore);
    }

    @GetMapping("/{leaderboardId}/users/{userId}/position")
    public Integer getLeaderboardUserPosition(@PathVariable String leaderboardId,
                                              @PathVariable String userId) {
        return leaderboardService.getLeaderboardUserById(leaderboardId, userId).getPosition();
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(badRequest(4000, e.getMessage()));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(LeaderboardNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleLeaderboardNotFoundException(LeaderboardNotFoundException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(badRequest(4001, e.getMessage()));
    }

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(LeaderboardUserNotFoundException.class)
    public ResponseEntity<ErrorResponse> handlePlayerNotFoundException(LeaderboardUserNotFoundException e) {
        return ResponseEntity
                .status(BAD_REQUEST)
                .body(badRequest(4002, e.getMessage()));
    }
}
