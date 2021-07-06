package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.leaderboard.infrastructure.PlayerRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.PlayerEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class PlayerService {

    private final PlayerRepository playerRepository;

    public UUID registerNewPlayer(NewPlayerRequest request) {
        PlayerEntity playerEntity = new PlayerEntity();
        playerEntity.setNick(request.getNick());
        PlayerEntity savedPlayer = playerRepository.save(playerEntity);
        return savedPlayer.getPlayerId();
    }
}
