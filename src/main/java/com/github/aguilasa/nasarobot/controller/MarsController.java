package com.github.aguilasa.nasarobot.controller;

import com.github.aguilasa.nasarobot.service.RobotMovementService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/mars")
public class MarsController {

    private final RobotMovementService robotMovementService;

    public MarsController(RobotMovementService robotMovementService) {
        this.robotMovementService = robotMovementService;
    }

    @PostMapping("/{command}")
    public ResponseEntity<String> moveRobot(@PathVariable("command") String command) {
        return ResponseEntity.ok(robotMovementService.moveRobot(command).toString());
    }
}
