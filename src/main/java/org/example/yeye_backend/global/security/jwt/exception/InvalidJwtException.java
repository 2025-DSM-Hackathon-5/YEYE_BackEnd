package org.example.yeye_backend.global.security.jwt.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class InvalidJwtException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new InvalidJwtException();

    private InvalidJwtException(){
        super(GeneralErrorCode.INVALID_TOKEN);
    }
}
