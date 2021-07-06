package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardOrPlayerNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardPlayerNotFoundException;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardPlayerRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.PlayerRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntity;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardPlayerEntity;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.PlayerEntity;
import com.gloot.springbootcodetest.leaderboard.infrastructure.mapper.LeaderboardMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.gloot.springbootcodetest.leaderboard.infrastructure.mapper.LeaderboardMapper.mapToDto;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class LeaderboardService {

    private static final String LEADERBOARD_NOT_FOUND_TEMPLATE = "Could not find leaderboard with id: %s";
    private static final String PLAYER_NOT_FOUND_TEMPLATE = "Could not find player with id: %s";

    private final LeaderboardEntryRepository leaderboardEntryRepository;
    private final LeaderboardPlayerRepository leaderboardPlayerRepository;
    private final LeaderboardRepository leaderboardRepository;
    private final PlayerRepository playerRepository;

    @Deprecated
    public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {
        List<LeaderboardEntryEntity> leaderboardEntries = leaderboardEntryRepository.findAll()
                .stream()
                .sorted(comparing(LeaderboardEntryEntity::getScore).reversed())
                .collect(toList());

        return IntStream.range(0, leaderboardEntries.size())
                .mapToObj(index -> mapToDto(index + 1, leaderboardEntries.get(index)))
                .collect(toList());
    }

    public List<LeaderboardDto> getAllLeaderboards() {
        return leaderboardRepository.findAll()
                .stream()
                .map(LeaderboardMapper::mapToLeaderboard)
                .collect(toList());
    }

    public UUID addLeaderboard(NewLeaderboardRequest request) {

        LeaderboardEntity leaderboard = new LeaderboardEntity();
        leaderboard.setGameName(request.getGameName());
        LeaderboardEntity savedLeaderboard = leaderboardRepository.save(leaderboard);

        return savedLeaderboard.getLeaderboardId();
    }

    public LeaderboardDto getLeaderboardById(UUID leaderboardId) {
        return leaderboardRepository.findById(leaderboardId)
                .map(LeaderboardMapper::mapToLeaderboard)
                .orElseThrow(() -> new LeaderboardNotFoundException(LEADERBOARD_NOT_FOUND_TEMPLATE + leaderboardId));
    }

    public PlayerDto getLeaderboardPlayerById(UUID leaderboardId, UUID playerId) {
        LeaderboardDto leaderboard = getLeaderboardById(leaderboardId);
        return leaderboard.getPlayers().stream()
                .filter(player -> player.getId().equals(playerId))
                .findFirst()
                .orElseThrow(() -> new LeaderboardPlayerNotFoundException(PLAYER_NOT_FOUND_TEMPLATE + playerId));
    }

    public Integer getLeaderboardPlayerPosition(UUID leaderboardId, UUID playerId) {
        return getLeaderboardPlayerById(leaderboardId, playerId).getPosition().orElse(null);
    }

    public void updatePlayerScore(UUID leaderboardId, UUID playerId, Integer newScore) {
        LeaderboardPlayerEntity leaderboardPlayerEntity = leaderboardPlayerRepository.findByLeaderboard_LeaderboardIdAndPlayer_PlayerId(leaderboardId, playerId)
                .orElseThrow(() -> new LeaderboardOrPlayerNotFoundException("Didn't find match for both leaderboard id: " + leaderboardId + "and player id: " + playerId));
        leaderboardPlayerEntity.setScore(newScore);
        leaderboardPlayerRepository.save(leaderboardPlayerEntity);
    }

    public void addPlayerToLeaderboard(UUID leaderboardId, UUID playerId, Optional<Integer> newScore) {
        LeaderboardEntity leaderboardEntity = leaderboardRepository
                .findById(leaderboardId)
                .orElseThrow(() -> new LeaderboardNotFoundException(String.format(LEADERBOARD_NOT_FOUND_TEMPLATE, leaderboardId)));

        PlayerEntity playerEntity = playerRepository
                .findById(playerId)
                .orElseThrow(() -> new LeaderboardPlayerNotFoundException(PLAYER_NOT_FOUND_TEMPLATE + playerId));

        LeaderboardPlayerEntity leaderboardPlayerEntity = new LeaderboardPlayerEntity();
        leaderboardPlayerEntity.setLeaderboard(leaderboardEntity);
        leaderboardPlayerEntity.setPlayer(playerEntity);
        newScore.ifPresent(leaderboardPlayerEntity::setScore);
        leaderboardPlayerRepository.save(leaderboardPlayerEntity);
    }
}
