package com.example.hyh.global.dto;

import com.example.hyh.global.exception.ErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Response<T> {

    private int resultCode;
    private String resultStatus;
    private T result;

    public static Response<Void> error(ErrorCode errorCode) {
        return new Response<>(errorCode.getValue(), errorCode.name(), null);
    }

    public static <T> Response<T> success(T result) {
        return new Response<>(200, "success", result);
    }
}
