package com.github.aguilasa.nasarobot.service;

import com.github.aguilasa.nasarobot.dto.Orientation;
import com.github.aguilasa.nasarobot.dto.Robot;
import com.github.aguilasa.nasarobot.exceptions.InvalidCommandException;
import com.github.aguilasa.nasarobot.exceptions.InvalidPositionException;
import org.springframework.stereotype.Service;

@Service
public class RobotMovementService {

    private static final int GRID_SIZE = 5;

    public Robot moveRobot(String command) {
        Robot robot = Robot.builder()
                .x(0)
                .y(0)
                .orientation(Orientation.N)
                .build();

        for (char c : command.toCharArray()) {
            switch (c) {
                case 'L' -> robot.setOrientation(robot.getOrientation().rotateLeft());
                case 'R' -> robot.setOrientation(robot.getOrientation().rotateRight());
                case 'M' -> moveForward(robot);
                default -> throw new InvalidCommandException("Invalid command: " + c);
            }

            if (!isValidPosition(robot.getX(), robot.getY())) {
                throw new InvalidPositionException("Invalid position: " + robot.toString());
            }
        }

        return robot;
    }

    private void moveForward(Robot robot) {
        switch (robot.getOrientation()) {
            case N -> robot.setY(robot.getY() + 1);
            case S -> robot.setY(robot.getY() - 1);
            case E -> robot.setX(robot.getX() + 1);
            case W -> robot.setX(robot.getX() - 1);
        }
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
    }
}
