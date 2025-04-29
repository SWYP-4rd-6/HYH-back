package com.example.hyh.transit.infra.apiclient.dto;

import com.example.hyh.transit.application.dto.SubwayRealTimeListResponse;
import com.example.hyh.transit.domain.openApi.SubwayRealTimeMessage;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SubwayRealTime(
        @JsonProperty("errorMessage")
        SubwayRealTimeMessage errMsg,

        @JsonProperty("realtimeArrivalList")
        List<SubwayRealTimeListResponse> arrivalList
) {
}
