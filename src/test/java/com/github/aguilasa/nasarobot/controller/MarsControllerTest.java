package com.github.aguilasa.nasarobot.controller;

import com.github.aguilasa.nasarobot.exceptions.InvalidCommandException;
import com.github.aguilasa.nasarobot.exceptions.InvalidPositionException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class MarsControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testMoveRobotRotationRight() throws Exception {
        performAndExpect("/mars/MMRMMRMM", "(2, 0, S)");
    }

    @Test
    void testMoveRobotLeft() throws Exception {
        performAndExpect("/mars/MML", "(0, 2, W)");
    }

    @Test
    void testRepeatMoveRobotLeft() throws Exception {
        performAndExpect("/mars/MML", "(0, 2, W)");
        performAndExpect("/mars/MML", "(0, 2, W)");
    }

    @Test
    void testMoveRobotRight() throws Exception {
        performAndExpect("/mars/MMR", "(0, 2, E)");
    }

    @Test
    void testMoveRobotForward() throws Exception {
        performAndExpect("/mars/MMM", "(0, 3, N)");
    }

    @Test
    void testInvalidCommand() throws Exception {
        mockMvc.perform(post("/mars/AAA")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidCommandException));
    }

    @Test
    void testInvalidPosition() throws Exception {
        mockMvc.perform(post("/mars/MMMMMMMMMMMMMMMMMMMMMMMM")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidPositionException));
    }

    @Test
    void testMoveRobotToEdge() throws Exception {
        performAndExpect("/mars/MMMM", "(0, 4, N)");
    }

    @Test
    void testMoveRobotOutOfEdge() throws Exception {
        mockMvc.perform(post("/mars/MMMMMM")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(result.getResolvedException() instanceof InvalidPositionException));
    }

    @Test
    void testMoveRobotTurnRight() throws Exception {
        performAndExpect("/mars/RRRR", "(0, 0, N)");
    }

    @Test
    void testMoveRobotRotateRightAndMove() throws Exception {
        performAndExpect("/mars/RM", "(1, 0, E)");
    }

    @Test
    void testMoveRobotEmptyCommand() throws Exception {
        performAndExpect("/mars/", "(0, 0, N)");
    }

    @Test
    void testMoveRobotSingleMove() throws Exception {
        performAndExpect("/mars/M", "(0, 1, N)");
    }

    private void performAndExpect(String path, String expectedOutput) throws Exception {
        ResultActions resultActions = mockMvc.perform(post(path)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        resultActions.andExpect(content().string(expectedOutput));
    }
}
