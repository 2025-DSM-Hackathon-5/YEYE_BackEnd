package org.example.yeye_backend.global.exception;

import java.time.LocalDateTime;

public record ExceptionResponse(
    int errorCode,
    String message,
    String description,
    LocalDateTime timeStamp
) {
    public static ExceptionResponse of(GlobalErrorCode e) {
        return new ExceptionResponse(e.getErrorCode(), e.getErrorMessage(), e.getErrorMessage(), LocalDateTime.now());
    }

    public static ExceptionResponse of(GlobalErrorCode e, String description) {
        return new ExceptionResponse(e.getErrorCode(), e.getErrorMessage(), description, LocalDateTime.now());
    }
}
