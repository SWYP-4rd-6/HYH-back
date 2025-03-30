package com.example.hyh.transit.application;

import com.example.hyh.transit.application.component.GyeongiBusComponent;
import com.example.hyh.transit.application.dto.*;
import com.example.hyh.transit.domain.BusStationRepository;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusStationQueryService {

    @Value("${real-time-gyeonggi-bus-api}")
    private String gyeongiBusKey;

    private final BusStationRepository busStationRepository;
    private final GyeongiBusComponent gyeongiBusComponent;

    public List<BusStationResponse> searchByStationName(String stationName, int limit) {
        return busStationRepository.searchByStationName(stationName, limit).stream()
                .map(BusStationResponse::of)
                .toList();
    }

    public List<BusStationResponse> searchNearestBusStations(double latitude, double longitude, int limit) {
        return busStationRepository.searchNearestBusStations(latitude, longitude, limit).stream()
                .map(BusStationResponse::of)
                .toList();
    }

    public List<GyeonggiBusStationIdResponse> getGyeongiBusStationId(String keyword) throws IOException {
        String jsonResponse = gyeongiBusComponent.getGyeongiBusStationId(gyeongiBusKey, keyword, "json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode busStationListNode = root.path("response").path("msgBody");

        GyeonggiBustStationResponse gyeonggiBustStationResponse = mapper.readValue(
                busStationListNode.traverse(), new TypeReference<GyeonggiBustStationResponse>() {}
        );

        return gyeonggiBustStationResponse.gyeonggiBusStationList();
    }

    public List<GyeonggiBusRealTimeListResponse> getGyeonggiBusRealTimeById(int stationId) throws IOException {
        String jsonResponse = gyeongiBusComponent.getGyeonggiBusRealtime(gyeongiBusKey, stationId, "json");
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(jsonResponse);
        JsonNode busStationListNode = root.path("response").path("msgBody");

        GyeonggiBusRealTimeResponse response = mapper.readValue(
                busStationListNode.traverse(), new TypeReference<GyeonggiBusRealTimeResponse>() {}
        );

        return response.gyeonggiBusRealTimeList();
    }
}
