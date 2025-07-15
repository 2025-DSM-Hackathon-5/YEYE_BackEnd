package org.example.yeye_backend.global.exception.generalExceptions;

import org.example.yeye_backend.global.exception.GlobalBusinessException;
import org.example.yeye_backend.global.exception.generalExceptions.errorCode.GeneralErrorCode;

public class ServiceUnavailableException extends GlobalBusinessException {
    public static final ServiceUnavailableException EXCEPTION = new ServiceUnavailableException();

    public ServiceUnavailableException() {
        super(GeneralErrorCode.SERVICE_UNAVAILABLE);
    }
}
