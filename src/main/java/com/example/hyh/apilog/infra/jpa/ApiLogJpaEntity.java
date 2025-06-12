package com.example.hyh.apilog.infra.jpa;

import com.example.hyh.apilog.domain.ApiLog;
import com.example.hyh.apilog.domain.ApiResponse;
import com.example.hyh.apilog.domain.ApiType;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "api_log")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiLogJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ApiType apiType;

    @Column(name = "api_url", nullable = false, length = 500)
    private String apiUrl;

    @Column(name = "http_method", nullable = false, length = 10)
    private String httpMethod;

    @Column(name = "request_params", columnDefinition = "TEXT")
    private String requestParams;

    @Column(name = "request_body", columnDefinition = "TEXT")
    private String requestBody;

    @Column(name = "request_headers", columnDefinition = "TEXT")
    private String requestHeaders;

    @Column(name = "is_success", nullable = false)
    private boolean isSuccess;

    @Column(name = "response_status", nullable = false)
    private Integer responseStatus;

    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody;

    @Column(name = "response_time_ms", nullable = false)
    private Long responseTimeMs;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;


    public static ApiLogJpaEntity from(ApiLog apiLog) {
        ApiLogJpaEntity entity = new ApiLogJpaEntity();
        entity.id = apiLog.getId();
        entity.apiType = apiLog.getApiType();
        entity.apiUrl = apiLog.getApiUrl();
        entity.httpMethod = apiLog.getHttpMethod();
        entity.requestParams = apiLog.getRequestParams();
        entity.requestBody = apiLog.getRequestBody();
        entity.requestHeaders = apiLog.getRequestHeaders();
        entity.responseStatus = apiLog.getResponse().getStatusCode();
        entity.responseBody = apiLog.getResponse().getResponseBody();
        entity.responseTimeMs = apiLog.getResponseTimeMs();
        entity.errorMessage = apiLog.getResponse().getErrorMessage();
        entity.createdBy = apiLog.getCreatedBy();
        entity.createdAt = apiLog.getCreatedAt();
        return entity;
    }

    public ApiLog toDomainModel() {
        return ApiLog.builder()
                .id(id)
                .apiType(apiType)
                .apiUrl(apiUrl)
                .httpMethod(httpMethod)
                .requestHeaders(requestHeaders)
                .requestBody(requestBody)
                .requestParams(requestParams)
                .response(isSuccess ?
                        ApiResponse.success(responseStatus, responseBody) :
                        ApiResponse.failure(responseStatus, responseBody, errorMessage))
                .responseTimeMs(responseTimeMs)
                .createdBy(createdBy)
                .createdAt(createdAt)
                .build();
    }

}
