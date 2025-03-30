package com.example.hyh.transit.application.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SubwayRealTimeResponse(
        @JsonProperty("errorMessage")
        SubwayRealTimeMessageResponse errMsg,

        @JsonProperty("realtimeArrivalList")
        List<SubwayRealTimeListResponse> arrivalList
) { }
