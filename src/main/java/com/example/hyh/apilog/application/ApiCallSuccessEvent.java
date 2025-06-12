package com.example.hyh.apilog.application;

import com.example.hyh.apilog.domain.ApiType;

import java.time.LocalDateTime;

public record ApiCallSuccessEvent(
        ApiType apiType,
        String apiUrl,
        String httpMethod,
        String requestParams,
        String requestHeaders,
        String requestBody,
        Integer responseStatus,
        String responseBody,
        Long responseTimeMs,
        String createdBy,
        LocalDateTime createdAt
) {
}
