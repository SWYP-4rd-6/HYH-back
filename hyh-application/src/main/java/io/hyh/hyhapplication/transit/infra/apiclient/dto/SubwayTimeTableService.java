package io.hyh.hyhapplication.transit.infra.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.hyh.hyhapplication.transit.application.dto.SubwayTimeTableResponse;
import io.hyh.hyhapplication.transit.domain.openApi.SubwayTimeTableMessage;

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
