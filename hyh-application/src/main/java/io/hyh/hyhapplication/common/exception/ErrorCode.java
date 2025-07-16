package io.hyh.hyhapplication.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_CHECKLIST("CH20000001", HttpStatus.CONFLICT, "Checklist is duplicated"),
    CHECKLIST_NOT_FOUND("CH20000002", HttpStatus.NOT_FOUND, "Check List is not founded"),

    MEMBER_NOT_FOUND("ME20000001", HttpStatus.NOT_FOUND, "Member is not founded"),

    NOT_AUTHORIZED("AT20000000", HttpStatus.UNAUTHORIZED, "not authorized"),
    INVALID_PERMISSION("AT20000001", HttpStatus.FORBIDDEN, "Permission is invalid"),
    INVALID_PROVIDER("AT20000002", HttpStatus.BAD_REQUEST, "invalid provider"),

    KAKAO_SERVER_AUTH_ERROR("AT30000001", HttpStatus.UNAUTHORIZED, "kakao server auth error"),
    KAKAO_SERVER_INTERNAL_ERROR("AT30000002", HttpStatus.UNAUTHORIZED, "kakao server has internal error"),

    ACCESS_TOKEN_EXPIRED("AT40000003", HttpStatus.UNAUTHORIZED, "access token is expired"),
    INVALID_REFRESH_TOKEN("AT40000004", HttpStatus.UNAUTHORIZED, "refresh token is invalid"),
    INVALID_TOKEN("AT40000005", HttpStatus.UNAUTHORIZED, "token is invalid"),

    API_REQUEST_FAIL("SE30000001", HttpStatus.INTERNAL_SERVER_ERROR, "Fail to call API"),

    INTERNAL_SERVER_ERROR("SE50000000", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
