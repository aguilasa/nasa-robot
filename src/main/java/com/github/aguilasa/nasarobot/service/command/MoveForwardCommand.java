package com.github.aguilasa.nasarobot.service.command;

import com.github.aguilasa.nasarobot.dto.Robot;

public class MoveForwardCommand extends Command {
    public MoveForwardCommand(Robot robot) {
        super(robot);
    }

    @Override
    public void execute() {
        switch (robot.getOrientation()) {
            case N -> robot.setY(robot.getY() + 1);
            case S -> robot.setY(robot.getY() - 1);
            case E -> robot.setX(robot.getX() + 1);
            case W -> robot.setX(robot.getX() - 1);
        }
    }
}
