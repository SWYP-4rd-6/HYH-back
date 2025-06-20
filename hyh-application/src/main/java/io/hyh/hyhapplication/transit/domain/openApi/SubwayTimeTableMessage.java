package io.hyh.hyhapplication.transit.domain.openApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubwayTimeTableMessage(
        @JsonProperty("CODE")
        String code,
        @JsonProperty("MESSAGE")
        String message
) {
}
