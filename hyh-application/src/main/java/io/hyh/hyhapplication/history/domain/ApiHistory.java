package io.hyh.hyhapplication.history.domain;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.JdbcTypeCode;
import org.hibernate.type.SqlTypes;

import java.time.LocalDateTime;
import java.util.Map;

@Entity
@Table(name = "api_history")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class ApiHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "api_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private ApiType apiType;

    @Column(name = "url", nullable = false, length = 500)
    private String url;

    @Column(name = "http_method", nullable = false, length = 10)
    private String httpMethod;

    @Column(name = "request_header", columnDefinition = "TEXT")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> requestHeader;

    @Column(name = "request_params", columnDefinition = "TEXT")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, Object> requestParams;

    @Column(name = "request_body", columnDefinition = "TEXT")
    private String requestBody;

    @Column(name = "is_success", nullable = false)
    private boolean isSuccess;

    @Column(name = "response_status")
    private Integer responseStatus;

    @Column(name = "response_header", columnDefinition = "TEXT")
    @JdbcTypeCode(SqlTypes.JSON)
    private Map<String, String> responseHeader;

    @Column(name = "response_body", columnDefinition = "TEXT")
    private String responseBody;

    @Column(name = "response_time", nullable = false)
    private Long responseTime;

    @Column(name = "error_message", columnDefinition = "TEXT")
    private String errorMessage;

    @Column(name = "created_by", length = 100)
    private String createdBy;

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt;

    @Builder
    public ApiHistory(ApiType apiType, String url, String httpMethod, Map<String, Object> requestParams,
                      Map<String, String> requestHeader, String requestBody, boolean isSuccess, Integer responseStatus,
                      Map<String, String> responseHeader, String responseBody, Long responseTime, String errorMessage, String createdBy) {
        this.apiType = apiType;
        this.url = url;
        this.httpMethod = httpMethod;
        this.requestParams = requestParams;
        this.requestHeader = requestHeader;
        this.requestBody = requestBody;
        this.isSuccess = isSuccess;
        this.responseStatus = responseStatus;
        this.responseHeader = responseHeader;
        this.responseBody = responseBody;
        this.responseTime = responseTime;
        this.errorMessage = errorMessage;
        this.createdBy = createdBy;
        this.createdAt = LocalDateTime.now();
    }


}


