package com.github.aguilasa.nasarobot.service.command;

import com.github.aguilasa.nasarobot.dto.Robot;

public class TurnLeftCommand extends Command {
    public TurnLeftCommand(Robot robot) {
        super(robot);
    }

    @Override
    public void execute() {
        robot.setOrientation(robot.getOrientation().rotateLeft());
    }
}
