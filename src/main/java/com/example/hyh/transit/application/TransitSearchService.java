package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.TransitSearchResult;
import com.example.hyh.transit.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class TransitSearchService {

    private final BusRouteRepository busRouteRepository;
    private final BusStationRepository busStationRepository;
    private final SubwayStationRepository subwayStationRepository;

    public TransitSearchResult search(String keyword) {
        List<BusRoute> busRouteResults = busRouteRepository.searchByRouteName(keyword, 10);
        List<BusStation> busStationResults = busStationRepository.searchByStationName(keyword, 10);
        List<SubwayStation> subwayStationResults = subwayStationRepository.searchBySubwayStationName(keyword, 10);

        return TransitSearchResult.of(busRouteResults, busStationResults, subwayStationResults);
    }

}
