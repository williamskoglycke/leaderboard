package com.gloot.springbootcodetest.leaderboard;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import java.util.List;

import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class LeaderboardEntryRepositoryTest extends SpringBootComponentTest {
  @Autowired
  LeaderboardEntryRepository repository;

  @Test
  void saveAndRetrieve() {
    LeaderboardEntryEntity entity = new LeaderboardEntryEntity("g-looter", 100);
    repository.saveAll(List.of(entity));
    LeaderboardEntryEntity fromRepository = repository.findById(entity.getUuid()).get();
    assertThat(fromRepository, is(entity));
  }
}
