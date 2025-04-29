package com.example.hyh.transit.domain.openApi;

import com.fasterxml.jackson.annotation.JsonProperty;

public record SubwayTimeTableMessage(
        @JsonProperty("CODE")
        String code,
        @JsonProperty("MESSAGE")
        String message
) {
}
