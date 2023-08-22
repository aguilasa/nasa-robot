package com.github.aguilasa.nasarobot.service.command;

import com.github.aguilasa.nasarobot.dto.Robot;

public class TurnRightCommand extends Command {
    public TurnRightCommand(Robot robot) {
        super(robot);
    }

    @Override
    public void execute() {
        robot.setOrientation(robot.getOrientation().rotateRight());
    }
}
