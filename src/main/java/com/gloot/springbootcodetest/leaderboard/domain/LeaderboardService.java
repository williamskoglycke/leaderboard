package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.leaderboard.domain.leaderboard.Leaderboard;
import com.gloot.springbootcodetest.leaderboard.domain.leaderboard.NewLeaderboardRequest;
import com.gloot.springbootcodetest.leaderboard.domain.player.Player;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardEntryRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.PlayerRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.IntStream;

import static com.gloot.springbootcodetest.leaderboard.infrastructure.mapper.LeaderboardEntryMapper.mapToDto;
import static java.util.Comparator.comparing;
import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class LeaderboardService {

    LeaderboardEntryRepository leaderboardEntryRepository;
    LeaderboardRepository leaderboardRepository;
    PlayerRepository playerRepository;


    public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {
        List<LeaderboardEntryEntity> leaderboardEntries = leaderboardEntryRepository.findAll()
                .stream()
                .sorted(comparing(LeaderboardEntryEntity::getScore).reversed())
                .collect(toList());

        return IntStream.range(0, leaderboardEntries.size())
                .mapToObj(index -> mapToDto(index + 1, leaderboardEntries.get(index)))
                .collect(toList());
    }

    public List<Leaderboard> getAllLeaderboards() {
        // TODO
        return null;
    }

    public UUID addLeaderboard(NewLeaderboardRequest request) {
        // TODO
        return null;
    }

    public Leaderboard getLeaderboardById(String leaderboardId) {
        // TODO
        return null;
    }

    public Player getLeaderboardPlayerById(String leaderboardId, String playerId) {
        // TODO
        return null;
    }

    public int getLeaderboardPlayerPosition(String leaderboardId, String playerId) {
        // TODO
        return -1;
    }

    public void updatePlayerScore(String leaderboardId, String playerId, Integer newScore) {
        // TODO
    }

    public String addPlayerToLeaderboard(String leaderboardId, String playerId) {
        // TODO
        return null;
    }
}
