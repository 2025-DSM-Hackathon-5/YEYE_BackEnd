package org.example.yeye_backend.global.exception.generalExceptions;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class BadRequestException extends GlobalBusinessException {
    public static final BadRequestException EXCEPTION = new BadRequestException();

    public BadRequestException() {
        super(GeneralErrorCode.BAD_REQUEST);
    }
}
