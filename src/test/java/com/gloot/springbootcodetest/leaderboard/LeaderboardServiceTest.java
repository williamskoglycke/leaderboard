package com.gloot.springbootcodetest.leaderboard;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import java.util.List;

import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardService;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LeaderboardServiceTest extends SpringBootComponentTest {

  @Autowired
  LeaderboardEntryRepository repository;
  @Autowired
  LeaderboardService service;

  @Test
  void getLeaderboard() {
    List<LeaderboardEntryEntity> entities = List
        .of(new LeaderboardEntryEntity("g-looter-2", 90),
            new LeaderboardEntryEntity("g-looter-1", 100));
    repository.saveAll(entities);

    List<LeaderboardEntryDto> leaderboard = service
        .getListOfAllLeaderboardEntriesAsDTO();
    assertEquals(entities.size(), leaderboard.size());
    // Verify ordering by score
    assertEqual(1, entities.get(1), leaderboard.get(0));
    assertEqual(2, entities.get(0), leaderboard.get(1));
  }

  private void assertEqual(int pos, LeaderboardEntryEntity entity, LeaderboardEntryDto dto) {
    assertEquals(pos, dto.getPosition());
    assertEquals(entity.getNick(), dto.getNick());
    assertEquals(entity.getScore(), dto.getScore());
  }
}
