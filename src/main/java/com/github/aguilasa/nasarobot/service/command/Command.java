package com.github.aguilasa.nasarobot.service.command;

import com.github.aguilasa.nasarobot.dto.Robot;
import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public abstract class Command {

    protected final Robot robot;

    public abstract void execute();

}
