package org.example.yeye_backend.global.exception.generalExceptions.errorCode;

import lombok.AllArgsConstructor;
import org.example.yeye_backend.global.exception.GlobalErrorCode;

@AllArgsConstructor
public enum GeneralErrorCode implements GlobalErrorCode {
    BAD_REQUEST(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "서버에 에러가 발생했습니다."),
    SERVICE_UNAVAILABLE(503, "서비스를 사용할 수 없습니다.");

    private final int ErrorCode;
    private final String ErrorMessage;

    @Override
    public int getErrorCode() {
        return ErrorCode;
    }

    @Override
    public String getErrorMessage() {
        return ErrorMessage;
    }
}
