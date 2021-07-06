package com.gloot.springbootcodetest.leaderboard.api.v2;

import com.gloot.springbootcodetest.leaderboard.domain.NewPlayerRequest;
import com.gloot.springbootcodetest.leaderboard.domain.PlayerService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static com.gloot.springbootcodetest.leaderboard.util.TestHelper.TEST_UUID;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlayerController.class)
class PlayerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlayerService playerService;

    @Test
    void registerNewPlayer() throws Exception {
        Mockito
                .when(playerService.registerNewPlayer(new NewPlayerRequest("RandyRavageTheSavage")))
                .thenReturn(UUID.fromString(TEST_UUID));

        mockMvc.perform(post("/api/v2/players")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"nick\": \"RandyRavageTheSavage\" }"))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.playerId", is(TEST_UUID)));
    }
}