package io.hyh.hyhapplication.transit.infra.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import io.hyh.hyhapplication.transit.domain.openApi.RealTimeGyeonggiBusListAtStation;
import io.hyh.hyhapplication.transit.domain.openApi.RealTimeGyeonggiBusListAtStationHeaderMessage;

import java.util.List;

public record RealTimeGyeonggiBusListAtStationService(
        @JsonProperty("response")
        RealTimeGyeonggiBusListAtStationResponse response
) {
    @JsonIgnoreProperties(ignoreUnknown = true)
    public record RealTimeGyeonggiBusListAtStationResponse(
            @JsonProperty("msgHeader")
            RealTimeGyeonggiBusListAtStationHeaderMessage headerMsg,
            @JsonProperty("msgBody")
            RealTimeGyeonggiBusListAtStationBodyMessage bodyMsg
    ) {
        public record RealTimeGyeonggiBusListAtStationBodyMessage(
                @JacksonXmlElementWrapper(useWrapping = false)
                @JsonProperty("busArrivalList")
                List<RealTimeGyeonggiBusListAtStation> realTimeGyeonggiBusListAtStations
        ) {
        }
    }
}
