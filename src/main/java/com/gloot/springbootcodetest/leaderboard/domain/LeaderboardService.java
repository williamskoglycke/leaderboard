package com.gloot.springbootcodetest.leaderboard.domain;

import com.gloot.springbootcodetest.leaderboard.infrastructure.LeaderboardRepository;
import com.gloot.springbootcodetest.leaderboard.infrastructure.entities.LeaderboardEntryEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static com.gloot.springbootcodetest.leaderboard.infrastructure.mapper.LeaderboardEntryMapper.mapToDto;

@Service
@AllArgsConstructor
public class LeaderboardService {

    private final LeaderboardRepository leaderboardRepository;

    public List<LeaderboardEntryDto> getListOfAllLeaderboardEntriesAsDTO() {
        List<LeaderboardEntryEntity> allEntries = leaderboardRepository.findAll();
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

    public List<LeaderboardDto> getAllLeaderboards() {
        // TODO
        return null;
    }

    public String addLeaderboard(LeaderboardRequest request) {
        // TODO
        return null;
    }

    public LeaderboardDto getLeaderboardById(String leaderboardId) {
        // TODO
        return null;
    }

    public LeaderboardEntryDto getLeaderboardUserById(String leaderboardId, String userId) {
        // TODO
        return null;
    }

    public void updateUserScore(String leaderboardId, String userId, Integer newScore) {
        // TODO
    }

    public String addNewUserToLeaderboard(String leaderboardId) {
        // TODO
        return null;
    }
}
