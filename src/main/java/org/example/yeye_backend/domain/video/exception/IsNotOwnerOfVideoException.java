package org.example.yeye_backend.domain.video.exception;

import org.example.yeye_backend.domain.video.exception.errorCode.VideoErrorCode;
import org.example.yeye_backend.global.exception.GlobalBusinessException;

public class IsNotOwnerOfVideoException extends GlobalBusinessException {
    public static final IsNotOwnerOfVideoException EXCEPTION = new IsNotOwnerOfVideoException();

    public IsNotOwnerOfVideoException() {
        super(VideoErrorCode.IS_NOT_OWNER_OF_VIDEO);
    }
}
