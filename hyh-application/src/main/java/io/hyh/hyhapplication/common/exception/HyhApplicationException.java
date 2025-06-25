package io.hyh.hyhapplication.common.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class HyhApplicationException extends RuntimeException {

    private ErrorCode errorCode;
    private String message;

    public HyhApplicationException(ErrorCode errorCode) {
        this.errorCode = errorCode;
        this.message = errorCode.getMessage();
    }

    @Override
    public String getMessage() {
        if (message == null) {
            return errorCode.getMessage();
        }

        return String.format("%s. %s", errorCode.getMessage(), message);
    }
}
