package com.gloot.springbootcodetest.leaderboard.infrastructure;

import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface LeaderboardRepository extends JpaRepository<LeaderboardEntity, UUID> {
}
