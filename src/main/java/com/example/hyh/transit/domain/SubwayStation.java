package com.example.hyh.transit.domain;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table(name = "subway_station")
@Getter
public class SubwayStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subway_id", nullable = false)
    private int subwayId;

    @Column(name = "subway_station_id", nullable = false)
    private Long subwayStationId;

    @Column(name = "subway_station_name", nullable = false)
    private String subwayStationName;

    @Column(name = "subway_station_line", nullable = false)
    private String subwayStationLine;

    @Column(name = "station_cd")
    private String stationCd;

    @Column(name = "fr_code")
    private String frCode;
}
