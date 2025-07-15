package org.example.yeye_backend.domain.auth.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class InvalidRefreshTokenException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new InvalidRefreshTokenException();

    private InvalidRefreshTokenException(){
        super(GeneralErrorCode.INVALID_REFRESH_TOKEN);
    }
}
