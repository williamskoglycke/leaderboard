package com.gloot.springbootcodetest.leaderboard.infrastructure;

import java.util.UUID;

import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeaderboardEntryRepository extends JpaRepository<LeaderboardEntryEntity, UUID> {}
