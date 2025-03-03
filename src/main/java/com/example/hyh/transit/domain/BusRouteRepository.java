package com.example.hyh.transit.domain;

import java.util.List;

public interface BusRouteRepository {

    List<BusRoute> searchByRouteName(String routeName, int limit);

}
