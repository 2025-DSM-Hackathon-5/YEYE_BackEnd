package org.example.yeye_backend.global.exception.generalExceptions;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class InternalServerErrorException extends GlobalBusinessException {
    public static final InternalServerErrorException EXCEPTION = new InternalServerErrorException();

    public InternalServerErrorException() {
        super(GeneralErrorCode.INTERNAL_SERVER_ERROR);
    }
}
