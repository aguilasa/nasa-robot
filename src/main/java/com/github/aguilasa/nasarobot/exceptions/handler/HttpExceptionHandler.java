package com.github.aguilasa.nasarobot.exceptions.handler;

import com.github.aguilasa.nasarobot.exceptions.InvalidCommandException;
import com.github.aguilasa.nasarobot.exceptions.InvalidPositionException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class HttpExceptionHandler {

    @ExceptionHandler({InvalidCommandException.class, InvalidPositionException.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public HttpErrorResponse handleBadRequestException(RuntimeException ex, WebRequest request) {
        return HttpErrorResponse.builder()
                .message(ex.getMessage())
                .details(request.getDescription(false))
                .build();
    }
}
