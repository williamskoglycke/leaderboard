package com.gloot.springbootcodetest.leaderboard.api.v2;

import com.gloot.springbootcodetest.leaderboard.domain.PlayerService;
import com.gloot.springbootcodetest.leaderboard.domain.NewPlayerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.UUID;

import static com.gloot.springbootcodetest.leaderboard.api.Url.API_VERSION_2;

@RestController("playerControllerV2")
@RequestMapping(API_VERSION_2 + "/players")
@AllArgsConstructor
public class PlayerController {

    PlayerService playerService;

    @PostMapping
    public ResponseEntity<Map<String, UUID>> registerNewPlayer(@RequestBody NewPlayerRequest request) {
        UUID playerId = playerService.registerNewPlayer(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(Map.of("playerId", playerId));
    }

}
