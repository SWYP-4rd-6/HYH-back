package io.hyh.hyhapplication.transit.infra.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.hyh.hyhapplication.transit.application.dto.SubwayRealTimeListResponse;
import io.hyh.hyhapplication.transit.domain.openApi.SubwayRealTimeMessage;

import java.util.List;

public record SubwayRealTime(
        @JsonProperty("errorMessage")
        SubwayRealTimeMessage errMsg,

        @JsonProperty("realtimeArrivalList")
        List<SubwayRealTimeListResponse> arrivalList
) {
}
