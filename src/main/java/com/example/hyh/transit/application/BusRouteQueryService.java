package com.example.hyh.transit.application;

import com.example.hyh.transit.application.component.SeoulBusComponent;
import com.example.hyh.transit.application.dto.BusRouteResponse;
import com.example.hyh.transit.application.dto.SeoulBusRealTimeAllResponse;
import com.example.hyh.transit.domain.BusRouteRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusRouteQueryService {

    @Value("${real-time-bus-api}")
    private String seoulBusKey;

    private final BusRouteRepository busRouteRepository;
    private final SeoulBusComponent seoulBusComponent;

    public List<BusRouteResponse> searchByRouteName(String routeName, int limit) {
        return busRouteRepository.searchByRouteName(routeName, limit).stream()
                .map(BusRouteResponse::of)
                .toList();
    }

    public List<SeoulBusRealTimeAllResponse> searchSeoulBusRealTimeAllById(String routeId) throws JsonProcessingException {
        String response = seoulBusComponent.getSeoulBusRealTimeAll(seoulBusKey, routeId);
        XmlMapper mapper = new XmlMapper();
        JsonNode root = mapper.readTree(response);
        JsonNode seoulBuseListNode = root.path("msgBody").path("itemList");

        return mapper.convertValue(
                seoulBuseListNode, new TypeReference<List<SeoulBusRealTimeAllResponse>>() {}
        );
    }
}
