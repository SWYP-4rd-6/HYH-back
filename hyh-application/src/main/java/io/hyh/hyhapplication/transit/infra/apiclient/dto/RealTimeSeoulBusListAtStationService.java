package io.hyh.hyhapplication.transit.infra.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.hyh.hyhapplication.transit.domain.openApi.RealTimeSeoulBusListAtStation;
import io.hyh.hyhapplication.transit.domain.openApi.RealTimeSeoulBusListAtStationHeaderMessage;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "ServiceResult")
public record RealTimeSeoulBusListAtStationService(
        @JacksonXmlProperty(localName = "msgHeader")
        RealTimeSeoulBusListAtStationHeaderMessage headerMsg,
        @JacksonXmlProperty(localName = "msgBody")
        RealTimeSeoulBusListAtStationBodyMessage bodyMsg
) {
    public record RealTimeSeoulBusListAtStationBodyMessage(
            @JacksonXmlElementWrapper(useWrapping = false)
            @JacksonXmlProperty(localName = "itemList")
            List<RealTimeSeoulBusListAtStation> realTimeSeoulBusListAtStations
    ) {
    }
}
