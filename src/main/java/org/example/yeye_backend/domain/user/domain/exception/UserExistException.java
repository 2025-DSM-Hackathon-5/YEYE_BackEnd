package org.example.yeye_backend.domain.user.domain.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class UserExistException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new UserExistException();

    private UserExistException(){
        super(GeneralErrorCode.USER_EXIST);
    }
}
