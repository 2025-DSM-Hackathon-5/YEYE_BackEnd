package org.example.yeye_backend.global.security.jwt.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class ExpiredJwtException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new ExpiredJwtException();

    private ExpiredJwtException(){
        super(GeneralErrorCode.EXPIRED_JWT);
    }
}
