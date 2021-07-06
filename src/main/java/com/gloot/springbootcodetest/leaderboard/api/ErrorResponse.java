package com.gloot.springbootcodetest.leaderboard.api;

import lombok.Builder;
import lombok.Value;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Value
@Builder
public class ErrorResponse {

    int httpCode;
    String httpStatus;
    Integer errorCode;
    String message;

    public static ErrorResponse badRequest(int errorCode, String validationError) {
        return ErrorResponse.builder()
                .httpCode(BAD_REQUEST.value())
                .httpStatus(BAD_REQUEST.getReasonPhrase())
                .errorCode(errorCode)
                .message(validationError)
                .build();
    }

}
