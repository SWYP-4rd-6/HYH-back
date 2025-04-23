package com.example.hyh.transit.infra.apiclient.dto;

import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusListAtStation;
import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusListAtStationHeaderMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

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
