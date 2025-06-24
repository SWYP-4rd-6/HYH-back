package io.hyh.hyhapplication.common.apiclient;

import lombok.Builder;
import org.springframework.util.MultiValueMap;

import java.time.ZonedDateTime;
import java.util.Map;

@Builder
public record HttpClientRequestCompletedEvent(
        String url,
        String httpMethod,
        Map<String, Object> requestParams,
        MultiValueMap<String, String> requestHeaders,
        String requestBody,
        Integer responseStatus,
        MultiValueMap<String, String> responseHeaders,
        String responseBody,
        Long responseTime,
        String errorMessage,
        boolean isSuccess,
        ZonedDateTime createdAt
) {
}
