package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import com.gloot.springbootcodetest.leaderboard.errors.PlayerAlreadyInLeaderboardException;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardPlayerRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.PlayerRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertEquals;

class LeaderboardServiceTest extends SpringBootComponentTest {

    @Autowired
    private LeaderboardEntryRepository leaderboardEntryRepository;

    @Autowired
    private LeaderboardRepository leaderboardRepository;

    @Autowired
    private LeaderboardPlayerRepository leaderboardPlayerRepository;

    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private LeaderboardService leaderboardService;

    @Autowired
    private PlayerService playerService;

    @BeforeEach
    void setUp() {
        leaderboardPlayerRepository.deleteAll();
        leaderboardRepository.deleteAll();
        leaderboardPlayerRepository.deleteAll();
        leaderboardEntryRepository.deleteAll();
    }

    @Test
    void getLeaderboard() {
        List<LeaderboardEntryEntity> entities = List
                .of(new LeaderboardEntryEntity("g-looter-2", 90),
                        new LeaderboardEntryEntity("g-looter-1", 100),
                        new LeaderboardEntryEntity("g-looter-3", null));
        leaderboardEntryRepository.saveAll(entities);

        List<LeaderboardEntryDto> leaderboard = leaderboardService
                .getListOfAllLeaderboardEntriesAsDTO();
        assertEquals(entities.size(), leaderboard.size());
        // Verify ordering by score
        assertEqual(1, entities.get(1), leaderboard.get(0));
        assertEqual(2, entities.get(0), leaderboard.get(1));
        assertEqual(3, entities.get(2), leaderboard.get(2));
    }

    private void assertEqual(int pos, LeaderboardEntryEntity entity, LeaderboardEntryDto dto) {
        assertEquals(pos, dto.getPosition());
        assertEquals(entity.getNick(), dto.getNick());
        assertEquals(entity.getScore(), dto.getScore());
    }

    @Test
    void registerTwoPlayersToOneLeaderboard() {
        UUID leaderboardId = leaderboardService.addLeaderboard(new NewLeaderboardRequest("Overwatch2"));
        UUID player1Id = playerService.registerNewPlayer(new NewPlayerRequest("RandyRavage"));
        UUID player2Id = playerService.registerNewPlayer(new NewPlayerRequest("SandySavage"));
        leaderboardService.addPlayerToLeaderboard(leaderboardId, player1Id, Optional.empty());
        leaderboardService.addPlayerToLeaderboard(leaderboardId, player2Id, Optional.of(10));

        List<LeaderboardDto> allLeaderboards = leaderboardService.getAllLeaderboards();
        assertThat(allLeaderboards).hasSize(1);

        LeaderboardDto leaderboardDto = allLeaderboards.get(0);
        assertThat(leaderboardDto.getId()).isNotNull();
        assertThat(leaderboardDto.getGameName()).isEqualTo("Overwatch2");
        assertThat(leaderboardDto.getPlayers()).hasSize(2);

        PlayerDto player1Dto = leaderboardDto.getPlayers().get(0);
        assertThat(player1Dto.getId()).isNotNull();
        assertThat(player1Dto.getNick()).isEqualTo("SandySavage");
        assertThat(player1Dto.getPosition()).isEqualTo(1);
        assertThat(player1Dto.getScore()).isEqualTo(10);

        PlayerDto player2Dto = leaderboardDto.getPlayers().get(1);
        assertThat(player2Dto.getId()).isNotNull();
        assertThat(player2Dto.getNick()).isEqualTo("RandyRavage");
        assertThat(player2Dto.getPosition()).isEqualTo(2);
        assertThat(player2Dto.getScore()).isNull();
    }

    @Test
    void registerTwoPlayersToTwoLeaderboards() {
        UUID leaderboard1Id = leaderboardService.addLeaderboard(new NewLeaderboardRequest("Overwatch2"));
        UUID leaderboard2Id = leaderboardService.addLeaderboard(new NewLeaderboardRequest("Siege"));
        UUID player1Id = playerService.registerNewPlayer(new NewPlayerRequest("RandyRavage"));
        UUID player2Id = playerService.registerNewPlayer(new NewPlayerRequest("SandySavage"));
        leaderboardService.addPlayerToLeaderboard(leaderboard1Id, player1Id, Optional.empty());
        leaderboardService.addPlayerToLeaderboard(leaderboard1Id, player2Id, Optional.of(10));
        leaderboardService.addPlayerToLeaderboard(leaderboard2Id, player1Id, Optional.of(50));
        leaderboardService.addPlayerToLeaderboard(leaderboard2Id, player2Id, Optional.of(60));

        List<LeaderboardDto> allLeaderboards = leaderboardService.getAllLeaderboards();
        assertThat(allLeaderboards).hasSize(2);

        LeaderboardDto leaderboard1Dto = allLeaderboards.get(0);
        assertThat(leaderboard1Dto.getId()).isNotNull();
        assertThat(leaderboard1Dto.getGameName()).isEqualTo("Overwatch2");
        assertThat(leaderboard1Dto.getPlayers()).hasSize(2);

        PlayerDto player1Dto = leaderboard1Dto.getPlayers().get(0);
        assertThat(player1Dto.getId()).isNotNull();
        assertThat(player1Dto.getNick()).isEqualTo("SandySavage");
        assertThat(player1Dto.getPosition()).isEqualTo(1);
        assertThat(player1Dto.getScore()).isEqualTo(10);

        PlayerDto player2Dto = leaderboard1Dto.getPlayers().get(1);
        assertThat(player2Dto.getId()).isNotNull();
        assertThat(player2Dto.getNick()).isEqualTo("RandyRavage");
        assertThat(player2Dto.getPosition()).isEqualTo(2);
        assertThat(player2Dto.getScore()).isNull();

        LeaderboardDto leaderboard2Dto = allLeaderboards.get(1);
        assertThat(leaderboard2Dto.getId()).isNotNull();
        assertThat(leaderboard2Dto.getGameName()).isEqualTo("Siege");
        assertThat(leaderboard2Dto.getPlayers()).hasSize(2);

        PlayerDto player1DtoOnLeaderboard2 = leaderboard2Dto.getPlayers().get(0);
        assertThat(player1DtoOnLeaderboard2.getId()).isNotNull();
        assertThat(player1DtoOnLeaderboard2.getNick()).isEqualTo("SandySavage");
        assertThat(player1DtoOnLeaderboard2.getPosition()).isEqualTo(1);
        assertThat(player1DtoOnLeaderboard2.getScore()).isEqualTo(60);

        PlayerDto player2DtoOnLeaderboard2 = leaderboard2Dto.getPlayers().get(1);
        assertThat(player2DtoOnLeaderboard2.getId()).isNotNull();
        assertThat(player2DtoOnLeaderboard2.getNick()).isEqualTo("RandyRavage");
        assertThat(player2DtoOnLeaderboard2.getPosition()).isEqualTo(2);
        assertThat(player2DtoOnLeaderboard2.getScore()).isEqualTo(50);
    }

    @Test
    void playerAlreadyOnLeaderboard() {
        UUID leaderboardId = leaderboardService.addLeaderboard(new NewLeaderboardRequest("Quake III"));
        UUID playerId = playerService.registerNewPlayer(new NewPlayerRequest("RandyRavage"));
        leaderboardService.addPlayerToLeaderboard(leaderboardId, playerId, Optional.empty());

        assertThatExceptionOfType(PlayerAlreadyInLeaderboardException.class)
                .isThrownBy(() -> leaderboardService.addPlayerToLeaderboard(leaderboardId, playerId, Optional.empty()));
    }

    @Test
    void updateScoreTest() {
        UUID leaderboardId = leaderboardService.addLeaderboard(new NewLeaderboardRequest("Overwatch2"));
        UUID playerId = playerService.registerNewPlayer(new NewPlayerRequest("RandyRavage"));
        leaderboardService.addPlayerToLeaderboard(leaderboardId, playerId, Optional.empty());

        PlayerDto player = leaderboardService.getLeaderboardPlayerById(leaderboardId, playerId);
        assertThat(player.getScore()).isNull();

        leaderboardService.updatePlayerScore(leaderboardId, playerId, 1337);

        PlayerDto playerAfterUpdate = leaderboardService.getLeaderboardPlayerById(leaderboardId, playerId);
        assertThat(playerAfterUpdate.getScore()).isEqualTo(1337);
    }

    @Test
    void getPlayerPosition() {
        UUID leaderboardId = leaderboardService.addLeaderboard(new NewLeaderboardRequest("Overwatch2"));
        UUID player1Id = playerService.registerNewPlayer(new NewPlayerRequest("RandyRavage"));
        leaderboardService.addPlayerToLeaderboard(leaderboardId, player1Id, Optional.empty());

        // Position one
        PlayerDto player1 = leaderboardService.getLeaderboardPlayerById(leaderboardId, player1Id);
        assertThat(player1.getPosition()).isEqualTo(1);

        // New player with better score
        UUID player2Id = playerService.registerNewPlayer(new NewPlayerRequest("Stevie Nicks"));
        leaderboardService.addPlayerToLeaderboard(leaderboardId, player2Id, Optional.of(10));

        // Player one should be pushed down to second place
        PlayerDto player2 = leaderboardService.getLeaderboardPlayerById(leaderboardId, player1Id);
        assertThat(player2.getPosition()).isEqualTo(2);
    }
}
