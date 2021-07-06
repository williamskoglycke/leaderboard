package com.gloot.springbootcodetest.leaderboard.api;

import com.gloot.springbootcodetest.leaderboard.errors.ValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(ValidationException.class)
    public ErrorResponse handleValidationError(ValidationException e) {
        return ErrorResponse.badRequest(4000, e.getMessage());
    }

}
