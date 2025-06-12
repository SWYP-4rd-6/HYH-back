package com.example.hyh.apilog.application;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpRequest;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Component
@RequiredArgsConstructor
@Slf4j
public class ApiCallLoggingInterceptor implements ClientHttpRequestInterceptor {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public ClientHttpResponse intercept(
            HttpRequest request,
            byte[] body,
            ClientHttpRequestExecution execution) throws IOException {

        long startTime = System.currentTimeMillis();
        String requestBody = new String(body, StandardCharsets.UTF_8);

        try {
            ClientHttpResponse response = execution.execute(request, body);
            long responseTime = System.currentTimeMillis() - startTime;

            // 응답 본문을 읽기 위한 래퍼 사용
            ClientHttpResponse wrappedResponse = new BufferingClientHttpResponseWrapper(response);
            String responseBody = getResponseBody(wrappedResponse);

            // 성공/실패 여부에 따라 이벤트 발행
            if (isSuccessStatus(wrappedResponse.getStatusCode())) {
                publishSuccessEvent(request, wrappedResponse, requestBody, responseBody, responseTime);
            } else {
                publishFailEvent(request, wrappedResponse, requestBody, responseBody, responseTime);
            }

            return wrappedResponse;

        } catch (Exception e) {
            long responseTime = System.currentTimeMillis() - startTime;
            publishExceptionEvent(request, requestBody, responseTime, e);
            throw e;
        }
    }
}
