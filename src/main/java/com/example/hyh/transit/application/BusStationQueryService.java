package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.BusStationResponse;
import com.example.hyh.transit.domain.BusStationRepository;
import com.example.hyh.transit.domain.openApi.RealTimeGyeonggiBusListAtStation;
import com.example.hyh.transit.domain.openApi.RealTimeSeoulBusListAtStation;
import com.example.hyh.transit.infra.apiclient.GyeonggiBusApiClient;
import com.example.hyh.transit.infra.apiclient.SeoulBusApiClient;
import com.example.hyh.transit.infra.apiclient.dto.RealTimeGyeonggiBusListAtStationService;
import com.example.hyh.transit.infra.apiclient.dto.RealTimeSeoulBusListAtStationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusStationQueryService {

    @Value("${bus-api-key}")
    private String busKey;

    private final BusStationRepository busStationRepository;
    private final GyeonggiBusApiClient gyeonggiBusApiClient;
    private final SeoulBusApiClient seoulBusApiClient;

    public List<BusStationResponse> searchByStationName(String stationName, int limit) {
        List<BusStationResponse> busStationResponseList = busStationRepository.searchByStationName(stationName, limit).stream()
                .map(BusStationResponse::of)
                .collect(Collectors.toList());

        return busStationResponseList;
    }

    public List<BusStationResponse> searchNearestBusStations(double latitude, double longitude, int limit) {
        return busStationRepository.searchNearestBusStations(latitude, longitude, limit).stream()
                .map(BusStationResponse::of)
                .toList();
    }

    public List<RealTimeSeoulBusListAtStation> searchRealSeoulBusListByStationId(int stId) {
        RealTimeSeoulBusListAtStationService service = seoulBusApiClient.getRealTimeSeoulBusListByStationId(busKey, stId);

        return service.bodyMsg().realTimeSeoulBusListAtStations();
    }

    public List<RealTimeGyeonggiBusListAtStation> searchRealGyeonggiBusListByStationId(int stId) {
        RealTimeGyeonggiBusListAtStationService service = gyeonggiBusApiClient.getRealTimeGyeonggiBusListByStationId(busKey, stId, "json");

        return service.response().bodyMsg().realTimeGyeonggiBusListAtStations();
    }
}
