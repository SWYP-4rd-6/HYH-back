package com.example.hyh.transit.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GyeonggiBustStationResponse(
        @JsonProperty("busStationList")
        List<GyeonggiBusStationIdResponse> gyeonggiBusStationList
) {}
