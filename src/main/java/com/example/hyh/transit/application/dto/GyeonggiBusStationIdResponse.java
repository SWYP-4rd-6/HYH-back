package com.example.hyh.transit.application.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public record GyeonggiBusStationIdResponse(
        @JsonProperty("stationId")
        String nodeId,
        @JsonProperty("mobileNo")
        String arsId,
        String stationName,
        @JsonProperty("x")
        double latitude,
        @JsonProperty("y")
        double longitude
) {
}
