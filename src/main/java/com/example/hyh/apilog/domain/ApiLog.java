package com.example.hyh.apilog.domain;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ApiLog {

    private final Long id;
    private final ApiType apiType;
    private final String apiUrl;
    private final String httpMethod;
    private final String requestHeaders;
    private final String requestBody;
    private final String requestParams;
    private final ApiResponse response;
    private final Long responseTimeMs;
    private final String createdBy;
    private final LocalDateTime createdAt;


    @Builder
    public ApiLog(Long id,
                  ApiType apiType,
                  String apiUrl,
                  String httpMethod,
                  String requestHeaders,
                  String requestParams,
                  String requestBody,
                  ApiResponse response,
                  Long responseTimeMs,
                  String createdBy,
                  LocalDateTime createdAt) {
        validate(apiType, apiUrl, httpMethod, response, responseTimeMs);
        this.id = id;
        this.apiType = apiType;
        this.apiUrl = apiUrl;
        this.httpMethod = httpMethod;
        this.requestHeaders = requestHeaders;
        this.requestBody = requestBody;
        this.requestParams = requestParams;
        this.response = response;
        this.responseTimeMs = responseTimeMs;
        this.createdBy = createdBy;
        this.createdAt = createdAt;
    }


    private void validate(ApiType apiType, String apiUrl, String httpMethod, ApiResponse response, Long responseTimeMs) {
        if (apiType == null) {
            throw new IllegalArgumentException("API Type is null");
        }
        if (apiUrl == null || apiUrl.trim().isEmpty()) {
            throw new IllegalArgumentException("apiUrl is null or empty");
        }
        if (httpMethod == null || httpMethod.trim().isEmpty()) {
            throw new IllegalArgumentException("http method is null");
        }
        if (response == null) {
            throw new IllegalArgumentException("response is null");
        }
        if (responseTimeMs == null || responseTimeMs < 0) {
            throw new IllegalArgumentException("responseTimeMs is null or negative");
        }
    }

}
