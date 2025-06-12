package com.example.hyh.apilog.application;

import com.example.hyh.apilog.domain.ApiType;

import java.time.LocalDateTime;

public record ApiCallFailEvent(
        ApiType apiType,
        String apiUrl,
        String httpMethod,
//        Map<String, Object> requestParams,
//        Map<String, Object> requestHeaders,
        String requestParams,
        String requestHeaders,
        String requestBody,
        Integer responseStatus,
        String responseBody,
        String errorMessage,
        Long responseTimeMs,
        String createdBy,
        LocalDateTime createdAt
) {
}
