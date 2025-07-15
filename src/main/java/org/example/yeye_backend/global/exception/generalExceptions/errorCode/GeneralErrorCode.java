package org.example.yeye_backend.global.exception.generalExceptions.errorCode;

import lombok.AllArgsConstructor;
import org.example.yeye_backend.global.exception.GlobalErrorCode;

@AllArgsConstructor
public enum GeneralErrorCode implements GlobalErrorCode {
    BAD_REQUEST(400, "잘못된 요청입니다."),
    INTERNAL_SERVER_ERROR(500, "서버에 에러가 발생했습니다."),
    SERVICE_UNAVAILABLE(503, "서비스를 사용할 수 없습니다."),

    EXPIRED_JWT(401, "로그인 세션이 만료되었습니다."),
    INVALID_TOKEN(401, "유효하지 않는 엑세스 토큰입니다."),
    INVALID_REFRESH_TOKEN(401, "유효하지 않는 리프레시 토큰입니다."),
    REFRESH_TOKEN_NOT_FOUND(404, "리프레시 존재하지 않습니다."),

    USER_NOT_FOUND(404, "일치하는 유저를 찾을 수 없습니다."),
    USER_EXIST(409, "유저가 이미 존재합니다."),
    PASSWORD_MISMATCH(401, "비밀번호가 틀렸습니다."),

    SESSION_NOT_FOUNT(404, "해당 채팅 세션이 존재하지 않습니다.");

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
