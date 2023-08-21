package com.github.aguilasa.nasarobot.exceptions.handler;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class HttpErrorResponse {

    private String message;
    private String details;
}
