package org.example.yeye_backend.domain.auth.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class RefreshTokenNotFoundException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new RefreshTokenNotFoundException();

    private RefreshTokenNotFoundException(){
        super(GeneralErrorCode.REFRESH_TOKEN_NOT_FOUND);
    }
}
