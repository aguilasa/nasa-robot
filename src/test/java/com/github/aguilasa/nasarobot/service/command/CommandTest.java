package com.github.aguilasa.nasarobot.service.command;

import com.github.aguilasa.nasarobot.dto.Orientation;
import com.github.aguilasa.nasarobot.dto.Robot;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CommandTest {

    private Robot robot;

    @BeforeEach
    void setUp() {
        robot = Robot.builder()
                .x(0)
                .y(0)
                .orientation(Orientation.N)
                .build();
    }

    @Test
    void testMoveForwardCommand() {
        Command moveForward = new MoveForwardCommand(robot);
        moveForward.execute();
        assertEquals(1, robot.getY());
    }

    @Test
    void testTurnLeftCommand() {
        Command turnLeft = new TurnLeftCommand(robot);
        turnLeft.execute();
        assertEquals(Orientation.W, robot.getOrientation());
    }

    @Test
    void testTurnRightCommand() {
        Command turnRight = new TurnRightCommand(robot);
        turnRight.execute();
        assertEquals(Orientation.E, robot.getOrientation());
    }
}
