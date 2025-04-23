package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.BusRouteResponse;
import com.example.hyh.transit.domain.BusRouteRepository;
import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusByRouteAllList;
import com.example.hyh.transit.infra.apiclient.SeoulBusApiClient;
import com.example.hyh.transit.infra.apiclient.dto.RealTimeSeoulBusService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusRouteQueryService {

    @Value("${bus-api-key}")
    private String seoulBusKey;

    private final BusRouteRepository busRouteRepository;
    private final SeoulBusApiClient seoulBusApiClient;

    public List<BusRouteResponse> searchByRouteName(String routeName, int limit) {
        return busRouteRepository.searchByRouteName(routeName, limit).stream()
                .map(BusRouteResponse::of)
                .toList();
    }

    public List<RealTimeSeoulBusByRouteAllList> searchRealSeoulBusByRouteAllList(String routeId) {
        RealTimeSeoulBusService service = seoulBusApiClient.getSeoulBusRealTimeAll(seoulBusKey, routeId);

        return service.bodyMsg().seoulBusByRouteAllList();
    }
}
