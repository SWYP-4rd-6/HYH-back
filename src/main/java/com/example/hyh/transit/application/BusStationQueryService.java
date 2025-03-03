package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.BusStationResponse;
import com.example.hyh.transit.domain.BusStationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusStationQueryService {

    private final BusStationRepository busStationRepository;

    public List<BusStationResponse> searchByStationName(String stationName) {
        return busStationRepository.searchByStationName(stationName, 10).stream()
                .map(BusStationResponse::of)
                .toList();
    }

    public List<BusStationResponse> searchNearestBusStations(double latitude, double longitude) {
        return busStationRepository.searchNearestBusStations(latitude, longitude, 10).stream()
                .map(BusStationResponse::of)
                .toList();
    }

}
