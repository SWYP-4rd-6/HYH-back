package com.example.hyh.transit.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record GyeonggiBusRealTimeResponse(

        @JsonProperty("busArrivalList")
        List<GyeonggiBusRealTimeListResponse> gyeonggiBusRealTimeList
) {}
