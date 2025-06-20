package io.hyh.hyhapplication.common;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class ClientLoggerRequestInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public @NotNull ClientHttpResponse intercept(@NotNull HttpRequest request,
        byte @NotNull [] body, ClientHttpRequestExecution execution) throws IOException {
        logRequest(request, body);
        ClientHttpResponse response = execution.execute(request, body);
        return logResponse(request, response);
    }

    private void logRequest(HttpRequest request, byte[] body) {
        log.debug("Request: {} {}", request.getMethod(), request.getURI());
        logHeaders(request.getHeaders());
        if (body.length > 0) {
            log.debug("Request body: {}", new String(body, StandardCharsets.UTF_8));
        }
    }

    private ClientHttpResponse logResponse(HttpRequest request, ClientHttpResponse response)
        throws IOException {
        log.debug("Response status: {}", response.getStatusCode());
        logHeaders(response.getHeaders());

        byte[] responseBody = response.getBody().readAllBytes();
        if (responseBody.length > 0) {
            log.debug("Response body: {}", new String(responseBody, StandardCharsets.UTF_8));
        }

        return new BufferingClientHttpResponseWrapper(response, responseBody);
    }

    private void logHeaders(HttpHeaders headers) {
        headers.forEach((name, values) -> values.forEach(value -> log.info("{}={}", name, value)));
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
