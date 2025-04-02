package com.example.hyh.transit.application;

import com.example.hyh.transit.application.dto.BusRouteResponse;
import com.example.hyh.transit.domain.BusRouteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class BusRouteQueryService {

    private final BusRouteRepository busRouteRepository;

    public List<BusRouteResponse> searchByRouteName(String routeName, int limit) {
        return busRouteRepository.searchByRouteName(routeName, limit).stream()
                .map(BusRouteResponse::of)
                .toList();
    }

}
