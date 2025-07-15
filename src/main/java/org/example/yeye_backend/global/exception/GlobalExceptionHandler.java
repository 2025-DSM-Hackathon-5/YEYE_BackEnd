package org.example.yeye_backend.global.exception;

import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalBusinessException.class)
    public final ResponseEntity<ExceptionResponse> globalBusinessExceptionHandler(GlobalBusinessException e) {
        GlobalErrorCode errorCode = e.errorCode;
        ExceptionResponse response = ExceptionResponse.of(errorCode);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(errorCode.getErrorCode()));
    }
}
