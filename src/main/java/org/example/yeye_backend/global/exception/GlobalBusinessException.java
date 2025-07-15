package org.example.yeye_backend.global.exception;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class GlobalBusinessException extends RuntimeException {

    public final GlobalErrorCode errorCode;
}
