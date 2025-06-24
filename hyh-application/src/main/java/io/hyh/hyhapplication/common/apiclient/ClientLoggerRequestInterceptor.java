package io.hyh.hyhapplication.common.apiclient;


import io.hyh.hyhapplication.common.exception.ErrorCode;
import io.hyh.hyhapplication.common.exception.HyhApplicationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.time.ZonedDateTime;

@Component
@RequiredArgsConstructor
@Slf4j
public class ClientLoggerRequestInterceptor implements ClientHttpRequestInterceptor {

    private final ApplicationEventPublisher eventPublisher;

    @Override
    public @NotNull ClientHttpResponse intercept(@NotNull HttpRequest request,
                                                 byte @NotNull [] body,
                                                 ClientHttpRequestExecution execution) throws IOException {
        long startTime = System.currentTimeMillis();
        BufferingClientHttpResponseWrapper bufferingResponse = null;
        String errorMessage = "";
        boolean isSuccess = true;
        try {
            ClientHttpResponse response = execution.execute(request, body);
            bufferingResponse = new BufferingClientHttpResponseWrapper(response, response.getBody().readAllBytes());
            return bufferingResponse;
        } catch (Exception e) {
            isSuccess = false;
            errorMessage = e.getMessage();
            throw new HyhApplicationException(ErrorCode.API_REQUEST_FAIL);
        } finally {
            long responseTime = System.currentTimeMillis() - startTime;
            publishRequestCompletedEvent(request, body, bufferingResponse, responseTime, errorMessage, isSuccess);
        }
    }

    private void publishRequestCompletedEvent(HttpRequest request,
                                              byte[] body,
                                              BufferingClientHttpResponseWrapper response,
                                              long responseTime,
                                              String errorMessage,
                                              boolean isSuccess) {
        try {
            HttpClientRequestCompletedEvent event = HttpClientRequestCompletedEvent.builder()
                    .url(request.getURI().toString())
                    .httpMethod(request.getMethod().name())
                    .requestParams(request.getAttributes())
                    .requestHeaders(request.getHeaders())
                    .requestBody(safeGetString(body))
                    .responseStatus(safeGetStatusCode(response))
                    .responseHeaders(safeGetResponseHeaders(response))
                    .responseBody(safeGetResponseBody(response))
                    .responseTime(responseTime)
                    .errorMessage(errorMessage)
                    .createdAt(ZonedDateTime.now())
                    .isSuccess(isSuccess)
                    .build();

            eventPublisher.publishEvent(event);

        } catch (Exception e) {
            log.warn("Failed to publish HTTP client request completed event", e);
        }
    }

    private String safeGetString(byte[] bytes) {
        if (bytes == null || bytes.length == 0) {
            return "";
        }
        return new String(bytes, StandardCharsets.UTF_8);
    }

    private int safeGetStatusCode(BufferingClientHttpResponseWrapper response) {
        if (response == null) {
            return 0; //TODO 적절한 기본값
        }
        try {
            return response.getStatusCode().value();
        } catch (Exception e) {
            return 0;
        }
    }

    private HttpHeaders safeGetResponseHeaders(BufferingClientHttpResponseWrapper response) {
        if (response == null) {
            return new HttpHeaders();
        }
        try {
            return response.getHeaders();
        } catch (Exception e) {
            return new HttpHeaders();
        }
    }

    private String safeGetResponseBody(BufferingClientHttpResponseWrapper response) {
        if (response == null) {
            return "";
        }
        try {
            return new String(response.getBody().readAllBytes(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            return "";
        }
    }

    private static class BufferingClientHttpResponseWrapper implements ClientHttpResponse {

        private final ClientHttpResponse response;
        private final byte[] body;

        public BufferingClientHttpResponseWrapper(ClientHttpResponse response, byte[] body) {
            this.response = response;
            this.body = body;
        }

        @Override
        public @NotNull InputStream getBody() throws IOException {
            return new ByteArrayInputStream(body);
        }

        @Override
        public @NotNull HttpHeaders getHeaders() {
            return response.getHeaders();
        }

        @Override
        public @NotNull HttpStatusCode getStatusCode() throws IOException {
            return response.getStatusCode();
        }

        @Override
        public @NotNull String getStatusText() throws IOException {
            return response.getStatusText();
        }

        @Override
        public void close() {
            response.close();
        }
    }
}
