package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.BusRouteResponse;
import com.example.hyh.transit.domain.BusRouteRepository;
import com.example.hyh.transit.domain.openApi.RealTimeGyeonggiBusListAtStation;
import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusByRouteAllList;
import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusListAtStation;
import com.example.hyh.transit.infra.component.GyeonggiBusComponent;
import com.example.hyh.transit.infra.component.SeoulBusComponent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusRouteQueryService {

    @Value("${bus-api-key}")
    private String seoulBusKey;

    private final BusRouteRepository busRouteRepository;
    private final SeoulBusComponent seoulBusComponent;
    private final GyeonggiBusComponent gyeonggiBusComponent;

    public List<BusRouteResponse> searchByRouteName(String routeName, int limit) {
        return busRouteRepository.searchByRouteName(routeName, limit).stream()
                .map(BusRouteResponse::of)
                .toList();
    }

    public List<RealTimeSeoulBusByRouteAllList> searchRealSeoulBusByRouteAllList(String routeId) throws JsonProcessingException {
        String response = seoulBusComponent.getSeoulBusRealTimeAll(seoulBusKey, routeId);
        XmlMapper mapper = new XmlMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode seoulBuseListNode = root.path("msgBody").path("itemList");

        return mapper.convertValue(
                seoulBuseListNode, new TypeReference<List<RealTimeSeoulBusByRouteAllList>>() {
                }
        );
    }

    public List<RealTimeSeoulBusListAtStation> searchSeoulBusRealTimeStation(String stationId, String routeId, int ord) throws JsonProcessingException {
        String response = seoulBusComponent.getSeoulBusRealTimeStation(seoulBusKey, stationId, routeId, ord);
        XmlMapper mapper = new XmlMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode seoulBuseListNode = root.path("msgBody").path("itemList");

        return mapper.convertValue(
                seoulBuseListNode, new TypeReference<List<RealTimeSeoulBusListAtStation>>() {
                }
        );
    }

    public List<RealTimeGyeonggiBusListAtStation> getGyeonggiBusRealTimeStation(int stationId, String routeId, int strOrder, String format) throws IOException {
        String jsonResponse = gyeonggiBusComponent.getGyeonggiBusRealtimeStation(seoulBusKey, stationId, routeId, strOrder, format);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode busStationListNode = root.path("response").path("msgBody").path("busArrivalItem");

        return mapper.readValue(
                busStationListNode.traverse(), new TypeReference<List<RealTimeGyeonggiBusListAtStation>>() {
                }
        );
    }
}
