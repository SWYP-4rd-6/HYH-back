package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.BusStationResponse;
import com.example.hyh.transit.domain.BusStationRepository;
import com.example.hyh.transit.domain.openApi.GyeonggiBusStation;
import com.example.hyh.transit.domain.openApi.RealTimeGyeonggiBusListAtStation;
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
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusStationQueryService {

    @Value("${real-time-bus-api}")
    private String busKey;

    private final BusStationRepository busStationRepository;
    private final GyeonggiBusComponent gyeonggiBusComponent;
    private final SeoulBusComponent seoulBusComponent;

    public List<BusStationResponse> searchByStationName(String stationName, int limit) throws IOException {
        List<BusStationResponse> busStationResponseList = busStationRepository.searchByStationName(stationName, limit).stream()
                .map(BusStationResponse::of)
                .collect(Collectors.toList());

        busStationResponseList.addAll(searchByGyeonggiStationName(stationName));

        return busStationResponseList;
    }

    public List<BusStationResponse> searchNearestBusStations(double latitude, double longitude, int limit) {
        return busStationRepository.searchNearestBusStations(latitude, longitude, limit).stream()
                .map(BusStationResponse::of)
                .toList();
    }

    public List<RealTimeSeoulBusListAtStation> searchRealSeoulBusListByStationId(int stId) throws JsonProcessingException {
        String response = seoulBusComponent.getRealTimeSeoulBusListByStationId(busKey, stId);
        XmlMapper mapper = new XmlMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode seoulBusListNode = root.path("msgBody").path("itemList");

        return mapper.convertValue(
                seoulBusListNode, new TypeReference<List<RealTimeSeoulBusListAtStation>>() {
                }
        );
    }

    public List<RealTimeGyeonggiBusListAtStation> searchRealGyeonggiBusListByStationId(int stId) throws IOException {
        String jsonResponse = gyeonggiBusComponent.getRealTimeGyeonggiBusListByStationId(busKey, stId, "json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode gyeonggiBusListNode = root.path("response").path("msgBody");

        return mapper.readValue(
                gyeonggiBusListNode.traverse(), new TypeReference<List<RealTimeGyeonggiBusListAtStation>>() {
                }
        );
    }


    public List<BusStationResponse> searchByGyeonggiStationName(String stationName) throws IOException {
        String jsonResponse = gyeonggiBusComponent.getGyeongiBusStationId(busKey, stationName, "json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode busStationListNode = root.path("response").path("msgBody").path("busStationList");

        List<GyeonggiBusStation> gyeonggiBusStationList = mapper.readValue(
                busStationListNode.traverse(), new TypeReference<List<GyeonggiBusStation>>() {
                }
        );
        return gyeonggiBusStationList.stream().map(BusStationResponse::of).collect(Collectors.toList());
    }
}
