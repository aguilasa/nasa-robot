package com.github.aguilasa.nasarobot.service;

import com.github.aguilasa.nasarobot.dto.Robot;
import com.github.aguilasa.nasarobot.exceptions.InvalidCommandException;
import com.github.aguilasa.nasarobot.exceptions.InvalidPositionException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RobotMovementServiceTest {

    private RobotMovementService robotMovementService;

    @BeforeEach
    void setUp() {
        robotMovementService = new RobotMovementService();
    }

    @Test
    void testMoveRobotRotationRight() {
        Robot result = robotMovementService.moveRobot("MMRMMRMM");
        assertEquals("(2, 0, S)", result.toString());
    }

    @Test
    void testMoveRobotLeft() {
        Robot result = robotMovementService.moveRobot("MML");
        assertEquals("(0, 2, W)", result.toString());
    }

    @Test
    void testRepeatMoveRobotLeft() {
        Robot result = robotMovementService.moveRobot("MML");
        assertEquals("(0, 2, W)", result.toString());

        result = robotMovementService.moveRobot("MML");
        assertEquals("(0, 2, W)", result.toString());
    }

    @Test
    void testInvalidCommand() {
        assertThrows(InvalidCommandException.class, () -> robotMovementService.moveRobot("AAA"));
    }

    @Test
    void testInvalidPosition() {
        assertThrows(InvalidPositionException.class, () -> robotMovementService.moveRobot("MMMMMMMMMMMMMMMMMMMMMM"));
    }
}
