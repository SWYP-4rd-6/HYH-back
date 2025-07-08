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

    INVALID_PERMISSION("AT20000001", HttpStatus.UNAUTHORIZED, "Permission is invalid"),

    API_REQUEST_FAIL("SE50000001", HttpStatus.INTERNAL_SERVER_ERROR, "Fail to call API"),

    INTERNAL_SERVER_ERROR("SE50000000", HttpStatus.INTERNAL_SERVER_ERROR, "Internal server error");

    private final String code;
    private final HttpStatus status;
    private final String message;
}
