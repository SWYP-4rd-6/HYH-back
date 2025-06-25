package io.hyh.hyhapplication.transit.infra.apiclient.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import io.hyh.hyhapplication.transit.domain.openApi.RealTimeSeoulBusByRouteAllList;
import io.hyh.hyhapplication.transit.domain.openApi.RealTimeSeoulBusHeaderMessage;

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
