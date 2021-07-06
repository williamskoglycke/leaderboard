package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.infrastructure.PlayerRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.PlayerEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class PlayerServiceTest extends SpringBootComponentTest {

    @Autowired
    PlayerRepository playerRepository;

    @Autowired
    PlayerService playerService;

    @Test
    void registerNewPlayerTest() {
        assertThat(playerRepository.findAll()).isEmpty();

        String nick = "nickAndTheFamily";
        playerService.registerNewPlayer(new NewPlayerRequest(nick));

        List<PlayerEntity> allEntities = playerRepository.findAll();
        assertThat(allEntities).hasSize(1);
        PlayerEntity playerEntity = allEntities.get(0);
        assertThat(playerEntity.getPlayerId()).isNotNull();
        assertThat(playerEntity.getNick()).isEqualTo(nick);
    }
}