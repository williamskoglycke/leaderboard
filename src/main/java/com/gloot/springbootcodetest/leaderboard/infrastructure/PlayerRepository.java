package com.gloot.springbootcodetest.leaderboard.infrastructure;

import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.PlayerEntity;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PlayerRepository extends CrudRepository<PlayerEntity, UUID> {
}
