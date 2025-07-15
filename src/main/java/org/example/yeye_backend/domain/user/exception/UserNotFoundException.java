package org.example.yeye_backend.domain.user.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class UserNotFoundException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new UserNotFoundException();

    private UserNotFoundException(){
        super(GeneralErrorCode.USER_NOT_FOUND);
    }
}
