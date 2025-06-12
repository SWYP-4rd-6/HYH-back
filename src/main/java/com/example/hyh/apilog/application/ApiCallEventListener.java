package com.example.hyh.apilog.application;

import com.example.hyh.apilog.domain.ApiLog;
import com.example.hyh.apilog.domain.ApiLogRepository;
import com.example.hyh.apilog.domain.ApiResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
class ApiCallEventListener {

    private final ApiLogRepository apiLogRepository;


    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleApiCallSuccessEvent(ApiCallSuccessEvent event) {
        log.info("Received API call success event: {}", event);

        apiLogRepository.save(ApiLog.builder()
                .apiType(event.apiType())
                .apiUrl(event.apiUrl())
                .httpMethod(event.httpMethod())
                .requestHeaders(String.valueOf(event.requestHeaders()))
                .requestParams(String.valueOf(event.requestParams()))
                .requestBody(event.requestBody())
                .response(ApiResponse.success(event.responseStatus(), event.responseBody()))
                .responseTimeMs(event.responseTimeMs())
                .createdBy(event.createdBy())
                .createdAt(event.createdAt())
                .build());
    }

    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void handleApiCallFailEvent(ApiCallFailEvent event) {
        log.info("Received API call fail event: {}", event);

        apiLogRepository.save(ApiLog.builder()
                .apiType(event.apiType())
                .apiUrl(event.apiUrl())
                .httpMethod(event.httpMethod())
                .requestHeaders(String.valueOf(event.requestHeaders()))
                .requestParams(String.valueOf(event.requestParams()))
                .requestBody(event.requestBody())
                .response(ApiResponse.failure(event.responseStatus(), event.responseBody(), event.errorMessage()))
                .responseTimeMs(event.responseTimeMs())
                .createdBy(event.createdBy())
                .createdAt(event.createdAt())
                .build());
    }

}
