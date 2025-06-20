package io.hyh.hyhapplication.transit.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "bus_route")
@Getter
public class BusRoute {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "route_id", nullable = false, length = 9)
    private String routeId;

    @Column(name = "route_name", nullable = false)
    private String routeName;

    @Column(name = "ord", nullable = false)
    private int ord;

    @Column(name = "node_id", nullable = false, length = 9)
    private String nodeId;

    @Column(name = "ars_id", nullable = false, length = 5)
    private String arsId;

    @Column(name = "station_name", nullable = false)
    private String stationName;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    protected BusRoute() {
    }

}
