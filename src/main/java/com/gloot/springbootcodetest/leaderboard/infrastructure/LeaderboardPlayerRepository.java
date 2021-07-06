package com.gloot.springbootcodetest.leaderboard.infrastructure;

import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardPlayerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;
import java.util.UUID;

public interface LeaderboardPlayerRepository extends CrudRepository<LeaderboardPlayerEntity, UUID> {
    Optional<LeaderboardPlayerEntity> findByLeaderboard_LeaderboardIdAndPlayer_PlayerId(UUID leaderboardId, UUID playerId);
}
