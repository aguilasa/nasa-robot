package com.github.aguilasa.nasarobot.service;

import com.github.aguilasa.nasarobot.dto.Orientation;
import com.github.aguilasa.nasarobot.dto.Robot;
import com.github.aguilasa.nasarobot.exceptions.InvalidCommandException;
import com.github.aguilasa.nasarobot.exceptions.InvalidPositionException;
import com.github.aguilasa.nasarobot.service.command.Command;
import com.github.aguilasa.nasarobot.service.command.MoveForwardCommand;
import com.github.aguilasa.nasarobot.service.command.TurnLeftCommand;
import com.github.aguilasa.nasarobot.service.command.TurnRightCommand;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RobotMovementService {

    private static final int GRID_SIZE = 5;

    public Robot moveRobot(String command) {
        Robot robot = Robot.builder()
                .x(0)
                .y(0)
                .orientation(Orientation.N)
                .build();

        List<Command> commands = parseCommands(command, robot);

        for (Command cmd : commands) {
            cmd.execute();

            if (!isValidPosition(robot.getX(), robot.getY())) {
                throw new InvalidPositionException("Invalid position: " + robot);
            }
        }

        return robot;
    }

    private List<Command> parseCommands(String command, Robot robot) {
        List<Command> commands = new ArrayList<>();
        for (char c : command.toCharArray()) {
            switch (c) {
                case 'L' -> commands.add(new TurnLeftCommand(robot));
                case 'R' -> commands.add(new TurnRightCommand(robot));
                case 'M' -> commands.add(new MoveForwardCommand(robot));
                default -> throw new InvalidCommandException("Invalid command: " + c);
            }
        }
        return commands;
    }

    private boolean isValidPosition(int x, int y) {
        return x >= 0 && x < GRID_SIZE && y >= 0 && y < GRID_SIZE;
    }
}
