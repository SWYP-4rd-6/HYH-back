package com.example.hyh.transit.infra.apiclient.dto;

import com.example.hyh.transit.application.dto.SubwayTimeTableResponse;
import com.example.hyh.transit.domain.openApi.SubwayTimeTableMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public record SubwayTimeTableService(
        @JsonProperty("SearchSTNTimeTableByIDService")
        SearchSTNTimeTableByIDService service
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record SearchSTNTimeTableByIDService(
            @JsonProperty("RESULT")
            SubwayTimeTableMessage subwayTimeTableMessage,
            @JsonProperty("row")
            List<SubwayTimeTableResponse> subwayTimetableList
    ) {
    }
}
