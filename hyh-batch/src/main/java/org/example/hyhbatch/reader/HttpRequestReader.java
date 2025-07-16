package org.example.hyhbatch.reader;

import io.hyh.hyhapplication.weather.infra.apiclient.dto.CommonResponseV2;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.item.database.AbstractPagingItemReader;
import org.springframework.util.CollectionUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.function.Function;

@Slf4j
public class HttpRequestReader<T> extends AbstractPagingItemReader<T> {

    private final Function<HttpPageRequest, CommonResponseV2<T>> apiCaller;


    public HttpRequestReader(Function<HttpPageRequest, CommonResponseV2<T>> apiCaller, int pageSize) {
        this.apiCaller = apiCaller;
        setPageSize(pageSize);
    }

    @Override
    protected void doReadPage() {
        init();

        List<T> pageItems = fetchPage(getPage());

        if (pageItems != null && !pageItems.isEmpty()) {
            results.addAll(pageItems);
            log.debug("Fetched {} items for page {}", pageItems.size(), getPage());
        } else {
            log.info("No items found for page {}", getPage());
        }
    }

    private List<T> fetchPage(int pageIndex) {
        int pageNo = pageIndex + 1;

        Map<String, Object> requestParams = new HashMap<>();
        requestParams.put("pageNo", pageNo);
        requestParams.put("numOfRows", getPageSize());

        HttpPageRequest pageRequest = new HttpPageRequest(requestParams);

        log.debug("Fetching page {} (pageNo: {}) with {} items per page",
                pageIndex, pageNo, getPageSize());

        try {
            CommonResponseV2<T> response = apiCaller.apply(pageRequest);

            if (response == null || response.response() == null ||
                    response.response().body() == null ||
                    response.response().body().items() == null ||
                    response.response().body().items().item() == null) {
                log.warn("Received empty response for page {}", pageNo);
                return List.of();
            }

            List<T> items = response.response().body().items().item();
            log.debug("Successfully fetched {} items from page {}", items.size(), pageNo);

            return items;
        } catch (Exception e) {
            log.error("Failed to fetch data for page {}: {}", pageNo, e.getMessage());
            throw e;
        }
    }

    private void init() {
        if (CollectionUtils.isEmpty(results)) {
            results = new CopyOnWriteArrayList<>();
        } else {
            results.clear();
        }
    }

}
