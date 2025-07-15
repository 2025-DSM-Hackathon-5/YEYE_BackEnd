package org.example.yeye_backend.domain.video.exception.errorCode;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.example.yeye_backend.global.exception.GlobalErrorCode;

@AllArgsConstructor
public enum VideoErrorCode implements GlobalErrorCode {
    IS_NOT_OWNER_OF_VIDEO(403, "비디오의 주인이 아닙니다."),
    VIDEO_NOT_FOUND(404, "비디오를 찾을 수 없습니다.");

    final int errorCode;
    final String errorMessage;

    @Override
    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getErrorMessage() {
        return errorMessage;
    }
}
