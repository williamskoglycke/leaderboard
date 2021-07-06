package com.gloot.springbootcodetest.leaderboard.api.v2;

import com.gloot.springbootcodetest.leaderboard.domain.PlayerService;
import com.gloot.springbootcodetest.leaderboard.domain.player.NewPlayerRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.UUID;

import static com.gloot.springbootcodetest.leaderboard.api.Url.API_VERSION_2;

@RestController
@RequestMapping(API_VERSION_2 + "/players")
@AllArgsConstructor
public class PlayerController {

    PlayerService playerService;

    @PostMapping
    public ResponseEntity<?> registerNewPlayer(@RequestBody NewPlayerRequest request) {
        UUID id = playerService.registerNewPlayer(request);
        return ResponseEntity.created(URI.create("/api/v2/players/" + id)).build();
    }

}
