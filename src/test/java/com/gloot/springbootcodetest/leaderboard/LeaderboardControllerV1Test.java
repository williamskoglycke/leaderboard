package com.gloot.springbootcodetest.leaderboard;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.gloot.springbootcodetest.SpringBootComponentTest;
import java.util.List;

import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;

public class LeaderboardControllerV1Test extends SpringBootComponentTest {

  @Autowired private MockMvc mockMvc;
  @Autowired LeaderboardEntryRepository repository;

  @Test
  void getLeaderboardTest() throws Exception {
    LeaderboardEntryEntity entity = new LeaderboardEntryEntity("g-looter", 100);
    repository.saveAll(List.of(entity));

    mockMvc.perform(get("/api/v1/leaderboard"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$.[0].position", is(1)))
        .andExpect(jsonPath("$.[0].nick", is(entity.getNick())))
        .andExpect(jsonPath("$.[0].score", is(entity.getScore())));
  }
}
