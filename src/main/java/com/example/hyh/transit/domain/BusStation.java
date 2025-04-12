package com.example.hyh.transit.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "bus_station")
@Getter
public class BusStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "node_id", nullable = false, length = 9)
    private String nodeId;

    @Column(name = "ars_id", nullable = false, length = 5)
    private String stId;

    @Column(name = "station_name", nullable = false)
    private String stationName;

    @Column(name = "latitude", nullable = false)
    private double latitude;

    @Column(name = "longitude", nullable = false)
    private double longitude;

    @Column(name = "station_type", nullable = false)
    @Enumerated(EnumType.STRING)
    private BusStationType stationType;

    protected BusStation() {
    }

}
