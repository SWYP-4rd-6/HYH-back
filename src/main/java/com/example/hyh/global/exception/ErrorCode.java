package com.example.hyh.global.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    DUPLICATED_CHECKLIST(HttpStatus.CONFLICT, HttpStatus.CONFLICT.value(), "Checklist is duplicated"),
    USER_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "User is not founded"),
    CHECKLIST_NOT_FOUND(HttpStatus.NOT_FOUND, HttpStatus.NOT_FOUND.value(), "Check List is not founded"),
    INVALID_PERMISSION(HttpStatus.UNAUTHORIZED, HttpStatus.UNAUTHORIZED.value(), "Permission is invalid"),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Internal server error");

    private final HttpStatus status;
    private final int value;
    private final String message;
}
