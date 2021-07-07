package com.gloot.springbootcodetest.leaderboard.infrastructure.mapper;

import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardDto;
import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardEntryDto;
import com.gloot.springbootcodetest.leaderboard.domain.PlayerDto;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntity;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardPlayerEntity;

import java.util.List;
import java.util.Set;
import java.util.stream.IntStream;

import static java.util.Comparator.comparing;
import static java.util.Comparator.nullsLast;
import static java.util.Comparator.reverseOrder;
import static java.util.stream.Collectors.toList;

public final class LeaderboardMapper {

    private LeaderboardMapper() {
    }

    @Deprecated
    public static LeaderboardEntryDto mapToDto(int pos, LeaderboardEntryEntity entity) {
        return LeaderboardEntryDto.builder()
                .position(pos)
                .nick(entity.getNick())
                .score(entity.getScore())
                .build();
    }

    public static LeaderboardDto mapToLeaderboard(LeaderboardEntity leaderboardEntity) {
        return new LeaderboardDto(
                leaderboardEntity.getGameName(),
                leaderboardEntity.getLeaderboardId(),
                mapToLeaderboardPlayers(leaderboardEntity.getLeaderboardPlayerEntities())
        );
    }

    private static List<PlayerDto> mapToLeaderboardPlayers(Set<LeaderboardPlayerEntity> leaderboardPlayerEntities) {
        if (leaderboardPlayerEntities == null || leaderboardPlayerEntities.isEmpty()) {
            return List.of();
        }

        List<LeaderboardPlayerEntity> playersSortedByScore = leaderboardPlayerEntities
                .stream()
                .sorted(comparing(LeaderboardPlayerEntity::getScore, nullsLast(reverseOrder())))
                .collect(toList());

        return IntStream.range(0, playersSortedByScore.size())
                .mapToObj(index -> {
                    LeaderboardPlayerEntity leaderboardPlayerEntity = playersSortedByScore.get(index);
                    return new PlayerDto(
                            leaderboardPlayerEntity.getPlayer().getNick(),
                            leaderboardPlayerEntity.getPlayer().getPlayerId(),
                            leaderboardPlayerEntity.getScore(),
                            index + 1);
                })
                .collect(toList());
    }
}
