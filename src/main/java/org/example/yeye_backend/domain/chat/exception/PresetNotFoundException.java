package org.example.yeye_backend.domain.chat.exception;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class PresetNotFoundException extends GlobalBusinessException {
    public final static GlobalBusinessException EXCEPTION = new PresetNotFoundException();
    private PresetNotFoundException(){
        super(GeneralErrorCode.PRESENT_NOT_FOUND);
    }
}
