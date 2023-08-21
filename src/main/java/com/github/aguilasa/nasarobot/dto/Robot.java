package com.github.aguilasa.nasarobot.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Robot {

    private int x;
    private int y;
    private Orientation orientation;

    @Override
    public String toString() {
        return String.format("(%d, %d, %s)", x, y, orientation.name());
    }
}
