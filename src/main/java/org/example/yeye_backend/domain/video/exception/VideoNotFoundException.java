package org.example.yeye_backend.domain.video.exception;

import org.example.yeye_backend.domain.video.exception.errorCode.VideoErrorCode;
import org.example.yeye_backend.global.exception.GlobalBusinessException;

public class VideoNotFoundException extends GlobalBusinessException {
    public static final VideoNotFoundException EXCEPTION = new VideoNotFoundException();

    public VideoNotFoundException() {
        super(VideoErrorCode.VIDEO_NOT_FOUND);
    }
}
