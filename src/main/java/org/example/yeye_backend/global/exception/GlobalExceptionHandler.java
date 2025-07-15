package org.example.yeye_backend.global.exception;

import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(GlobalBusinessException.class)
    public final ResponseEntity<ExceptionResponse> globalBusinessExceptionHandler(GlobalBusinessException e) {
        GlobalErrorCode errorCode = e.errorCode;
        ExceptionResponse response = ExceptionResponse.of(errorCode);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(errorCode.getErrorCode()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public final ResponseEntity<ExceptionResponse> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        String errorMessage = e.getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.joining(" "));

        ExceptionResponse response = ExceptionResponse.of(GeneralErrorCode.BAD_REQUEST, errorMessage);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }

    @ExceptionHandler(MissingServletRequestPartException.class)
    public final ResponseEntity<ExceptionResponse> missingServletRequestPartExceptionHandler(MissingServletRequestPartException e) {
        String errorMessage = "필요한 파일 데이터인 " + e.getRequestPartName() + "이 존재하지 않습니다.";

        ExceptionResponse response = ExceptionResponse.of(GeneralErrorCode.BAD_REQUEST, errorMessage);

        return new ResponseEntity<>(response, HttpStatusCode.valueOf(400));
    }
}
