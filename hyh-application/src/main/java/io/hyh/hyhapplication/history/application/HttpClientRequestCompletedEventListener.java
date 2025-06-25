package io.hyh.hyhapplication.history.application;

import io.hyh.hyhapplication.common.apiclient.HttpClientRequestCompletedEvent;
import io.hyh.hyhapplication.history.domain.ApiHistory;
import io.hyh.hyhapplication.history.domain.ApiHistoryRepository;
import io.hyh.hyhapplication.history.domain.ApiType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class HttpClientRequestCompletedEventListener {

    private final ApiHistoryRepository apiHistoryRepository;


    @EventListener
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void onEvent(HttpClientRequestCompletedEvent event) {
        log.debug("get HttpClientRequestCompletedEvent in history: {}", event);

        apiHistoryRepository.save(ApiHistory.builder()
                .apiType(ApiType.Weather)
                .url(event.url())
                .httpMethod(event.httpMethod())
                .requestHeader(event.requestHeaders().toSingleValueMap())
                .requestParams(event.requestParams())
                .requestBody(event.requestBody())
                .isSuccess(event.isSuccess())
                .responseStatus(event.responseStatus())
                .responseHeader(event.responseHeaders().toSingleValueMap())
                .responseBody(event.responseBody())
                .responseTime(event.responseTime())
                .errorMessage(event.errorMessage())
                .createdBy("system")
                .build());
    }


}
