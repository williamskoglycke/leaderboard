package com.gloot.springbootcodetest.leaderboard.api.v2;

import com.gloot.springbootcodetest.leaderboard.domain.LeaderboardService;
import com.gloot.springbootcodetest.leaderboard.domain.NewLeaderboardRequest;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardOrPlayerNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.LeaderboardPlayerNotFoundException;
import com.gloot.springbootcodetest.leaderboard.errors.PlayerAlreadyInLeaderboardException;
import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.gloot.springbootcodetest.leaderboard.util.TestHelper.TEST_UUID;
import static com.gloot.springbootcodetest.leaderboard.util.TestHelper.getLeaderboard;
import static com.gloot.springbootcodetest.leaderboard.util.TestHelper.getPlayer;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(LeaderboardController.class)
class LeaderboardControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LeaderboardService leaderboardService;

    @Test
    void getAllLeaderboards() throws Exception {
        Mockito
                .when(leaderboardService.getAllLeaderboards())
                .thenReturn(List.of());

        mockMvc.perform(get("/api/v2/leaderboards"))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    void addLeaderboard() throws Exception {
        Mockito
                .when(leaderboardService.addLeaderboard(new NewLeaderboardRequest("CS:GO")))
                .thenReturn(UUID.fromString(TEST_UUID));

        mockMvc.perform(post("/api/v2/leaderboards")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"gameName\": \"CS:GO\" }"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.leaderboardId", is(TEST_UUID)));
    }

    @Test
    void getLeaderboardById() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardById(any(UUID.class)))
                .thenReturn(getLeaderboard());

        mockMvc.perform(get("/api/v2/leaderboards/" + TEST_UUID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.gameName", is("DOTA2")))
                .andExpect(jsonPath("$.id", is(TEST_UUID)))
                .andExpect(jsonPath("$.players.size()", is(1)))
                .andExpect(jsonPath("$.players[0].nick", is("NickFury")))
                .andExpect(jsonPath("$.players[0].id", is(TEST_UUID)))
                .andExpect(jsonPath("$.players[0].score", is(1337)))
                .andExpect(jsonPath("$.players[0].position", is(1)));
    }

    @Test
    void getPlayersByLeaderboard() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardById(any(UUID.class)))
                .thenReturn(getLeaderboard());

        mockMvc.perform(get("/api/v2/leaderboards/" + TEST_UUID + "/players"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)))
                .andExpect(jsonPath("$.[0].nick", is("NickFury")))
                .andExpect(jsonPath("$.[0].id", is(TEST_UUID)))
                .andExpect(jsonPath("$.[0].score", is(1337)))
                .andExpect(jsonPath("$.[0].position", is(1)));
    }

    @Test
    void getLeaderboardPlayerById() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardPlayerById(any(UUID.class), any(UUID.class)))
                .thenReturn(getPlayer());

        mockMvc.perform(get("/api/v2/leaderboards/" + TEST_UUID + "/players/" + TEST_UUID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nick", is("NickFury")))
                .andExpect(jsonPath("$.id", is(TEST_UUID)))
                .andExpect(jsonPath("$.score", is(1337)))
                .andExpect(jsonPath("$.position", is(1)));
    }

    @Test
    void addPlayerToLeaderboard_withScore() throws Exception {

        mockMvc.perform(post("/api/v2/leaderboards/" + TEST_UUID + "/players/" + TEST_UUID).param("score", "100"))
                .andDo(print())
                .andExpect(status().isCreated());

        Mockito.verify(leaderboardService, times(1))
                .addPlayerToLeaderboard(UUID.fromString(TEST_UUID), UUID.fromString(TEST_UUID), Optional.of(100));
    }

    @Test
    void updateUserScore() throws Exception {
        mockMvc.perform(put("/api/v2/leaderboards/" + TEST_UUID + "/players/" + TEST_UUID).param("score", "100"))
                .andDo(print())
                .andExpect(status().isOk());

        Mockito.verify(leaderboardService, times(1))
                .updatePlayerScore(UUID.fromString(TEST_UUID), UUID.fromString(TEST_UUID), 100);
    }

    @Test
    void getLeaderboardUserPosition() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardPlayerPosition(any(UUID.class), any(UUID.class)))
                .thenReturn(getPlayer().getPosition());

        mockMvc.perform(get("/api/v2/leaderboards/" + TEST_UUID + "/players/" + TEST_UUID + "/position"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.position", is(1)));
    }

    @Test
    void handleValidationException() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardById(any(UUID.class)))
                .thenThrow(new ValidationException("message"));

        mockMvc.perform(get("/api/v2/leaderboards/" + TEST_UUID))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.httpCode", is(400)))
                .andExpect(jsonPath("$.httpStatus", is("Bad Request")))
                .andExpect(jsonPath("$.errorCode", is(4000)))
                .andExpect(jsonPath("$.message", is("message")));
    }

    @Test
    void handleLeaderboardNotFoundException() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardById(any(UUID.class)))
                .thenThrow(new LeaderboardNotFoundException("message"));

        mockMvc.perform(get("/api/v2/leaderboards/" + TEST_UUID))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.httpCode", is(400)))
                .andExpect(jsonPath("$.httpStatus", is("Bad Request")))
                .andExpect(jsonPath("$.errorCode", is(4001)))
                .andExpect(jsonPath("$.message", is("message")));
    }

    @Test
    void handlePlayerNotFoundException() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardById(any(UUID.class)))
                .thenThrow(new LeaderboardPlayerNotFoundException("message"));

        mockMvc.perform(get("/api/v2/leaderboards/" + TEST_UUID))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.httpCode", is(400)))
                .andExpect(jsonPath("$.httpStatus", is("Bad Request")))
                .andExpect(jsonPath("$.errorCode", is(4002)))
                .andExpect(jsonPath("$.message", is("message")));
    }

    @Test
    void handleLeaderboardOrPlayerNotFoundException() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardById(any(UUID.class)))
                .thenThrow(new LeaderboardOrPlayerNotFoundException("message"));

        mockMvc.perform(get("/api/v2/leaderboards/9604e4d8-a763-4d43-9def-6704f74bb09b"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.httpCode", is(400)))
                .andExpect(jsonPath("$.httpStatus", is("Bad Request")))
                .andExpect(jsonPath("$.errorCode", is(4003)))
                .andExpect(jsonPath("$.message", is("message")));
    }

    @Test
    void handlePlayerAlreadyInLeaderboardException() throws Exception {
        Mockito
                .when(leaderboardService.getLeaderboardById(any(UUID.class)))
                .thenThrow(new PlayerAlreadyInLeaderboardException("message"));

        mockMvc.perform(get("/api/v2/leaderboards/9604e4d8-a763-4d43-9def-6704f74bb09b"))
                .andDo(print())
                .andExpect(status().is4xxClientError())
                .andExpect(jsonPath("$.httpCode", is(400)))
                .andExpect(jsonPath("$.httpStatus", is("Bad Request")))
                .andExpect(jsonPath("$.errorCode", is(4004)))
                .andExpect(jsonPath("$.message", is("message")));
    }
}