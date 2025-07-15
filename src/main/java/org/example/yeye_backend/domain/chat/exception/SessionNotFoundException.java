package org.example.yeye_backend.domain.chat.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class SessionNotFoundException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new SessionNotFoundException();
    private SessionNotFoundException(){
        super(GeneralErrorCode.SESSION_NOT_FOUND);
    }
}
