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

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;

import static com.gloot.springbootcodetest.leaderboard.infrastructure.mapper.LeaderboardEntryMapper.mapToDto;

@Service
@AllArgsConstructor
public class LeaderboardService {

    LeaderboardEntryRepository leaderboardEntryRepository;
    LeaderboardRepository leaderboardRepository;
    PlayerRepository playerRepository;


    public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {
        List<LeaderboardEntryEntity> allEntries = leaderboardEntryRepository.findAll();
        Collections.sort(allEntries, new Comparator<LeaderboardEntryEntity>() {
            public int compare(LeaderboardEntryEntity e1, LeaderboardEntryEntity e2) {
                return e2.getScore() - e1.getScore();
            }
        });

        LeaderboardEntryEntity[] allEntriesAsEntities = allEntries.toArray(new LeaderboardEntryEntity[]{});
        LeaderboardEntryDto[] dtoObjects = new LeaderboardEntryDto[allEntriesAsEntities.length];

        for (int i = allEntriesAsEntities.length - 1; i >= 0; i--) {
            dtoObjects[i] = mapToDto(i + 1, allEntriesAsEntities[i]);
        }

        List<LeaderboardEntryDto> leaderboardEntryDtos = new ArrayList<>();
        for (int j = dtoObjects.length - 1; j >= 0; j--) {
            leaderboardEntryDtos.add(dtoObjects[j]);
        }

        Collections.sort(leaderboardEntryDtos, new Comparator<LeaderboardEntryDto>() {
            public int compare(LeaderboardEntryDto e1, LeaderboardEntryDto e2) {
                return e1.getPosition() - e2.getPosition();
            }
        });

        return leaderboardEntryDtos;
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
