package org.example.hyhbatch.reader;

import java.util.HashMap;
import java.util.Map;

public record HttpPageRequest(
        Map<String, Object> parameters
) {
    public HttpPageRequest(Map<String, Object> parameters) {
        if (parameters == null) {
            throw new IllegalArgumentException("Parameters must not be null");
        }
        if (parameters.get("pageNo") == null || parameters.get("numOfRows") == null) {
            throw new IllegalArgumentException("Parameters must contain 'pageNo' and 'numOfRows'");
        }
        this.parameters = new HashMap<>(parameters);
    }

    public int getPageNo() {
        return getParameter("pageNo", Integer.class);
    }

    public int getPageSize() {
        return getParameter("numOfRows", Integer.class);
    }

    public <T> T getParameter(String key, Class<T> type) {
        Object value = parameters.get(key);
        return type.cast(value);
    }

}
