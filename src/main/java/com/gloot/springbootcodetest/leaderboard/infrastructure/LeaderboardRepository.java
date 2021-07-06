package com.gloot.springbootcodetest.leaderboard.infrastructure;

import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface LeaderboardRepository extends CrudRepository<LeaderboardEntity, UUID> {
    Optional<LeaderboardEntity> findByLeaderboard_LeaderboardIdAndPlayer_PlayerId(UUID leaderboardId, UUID playerId);
}
