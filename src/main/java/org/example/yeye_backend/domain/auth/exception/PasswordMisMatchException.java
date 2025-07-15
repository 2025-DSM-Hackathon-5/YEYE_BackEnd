package org.example.yeye_backend.domain.auth.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class PasswordMisMatchException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new PasswordMisMatchException();

    private PasswordMisMatchException(){
        super(GeneralErrorCode.PASSWORD_MISMATCH);
    }
}
