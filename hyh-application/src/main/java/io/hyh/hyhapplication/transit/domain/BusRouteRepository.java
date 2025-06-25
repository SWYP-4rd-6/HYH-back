package io.hyh.hyhapplication.transit.domain;

import java.util.List;

public interface BusRouteRepository {

    List<BusRoute> searchByRouteName(String routeName, int limit);

}
