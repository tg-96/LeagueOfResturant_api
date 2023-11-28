package com.leagueofrestaurant.web.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Exception 발생했을때, 작동하는 클래스
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(LORException.class)
    public ResponseEntity<ErrorResponse> handle(LORException ex) {
        final ErrorCode errorCode = ex.getErrorCode();
        return new ResponseEntity<>(new ErrorResponse(errorCode, errorCode.getMessage()), HttpStatus.BAD_REQUEST);
    }

    @Data
    public class ErrorResponse {
        private final ErrorCode errorCode;
        private final String message;
    }
}
