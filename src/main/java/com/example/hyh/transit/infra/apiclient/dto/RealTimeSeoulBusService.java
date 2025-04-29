package com.example.hyh.transit.infra.apiclient.dto;

import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusByRouteAllList;
import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusHeaderMessage;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
@JacksonXmlRootElement(localName = "ServiceResult")
public record RealTimeSeoulBusService(
        @JacksonXmlProperty(localName = "msgHeader")
        RealTimeSeoulBusHeaderMessage headerMsg,
        @JacksonXmlProperty(localName = "msgBody")
        RealTimeSeoulBusBodyMessage bodyMsg
) {
    public record RealTimeSeoulBusBodyMessage(
            @JacksonXmlElementWrapper(useWrapping = false)
            @JacksonXmlProperty(localName = "itemList")
            List<RealTimeSeoulBusByRouteAllList> seoulBusByRouteAllList
    ) {
    }
}
