package com.example.hyh.apilog.domain;

import lombok.Getter;

@Getter
public class ApiResponse {
    private final Integer statusCode;
    private final String responseBody;
    private final String errorMessage;

    private ApiResponse(Integer statusCode, String responseBody, String errorMessage) {
        this.statusCode = statusCode;
        this.responseBody = responseBody;
        this.errorMessage = errorMessage;
    }

    public static ApiResponse success(Integer statusCode, String responseBody) {
        return new ApiResponse(statusCode, responseBody, null);
    }

    public static ApiResponse failure(Integer statusCode, String responseBody, String errorMessage) {
        return new ApiResponse(statusCode, responseBody, errorMessage);
    }

    public boolean isSuccess() {
        return statusCode >= 200 && statusCode < 300;
    }

    public boolean isClientError() {
        return statusCode >= 400 && statusCode < 500;
    }

    public boolean isServerError() {
        return statusCode >= 500;
    }

}
