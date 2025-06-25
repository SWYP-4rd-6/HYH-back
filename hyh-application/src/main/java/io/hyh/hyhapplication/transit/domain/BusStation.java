package io.hyh.hyhapplication.transit.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "bus_station")
@Getter
public class BusStation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "station_id")
    private String stationId;
    @Column(name = "origin_station_id")
    private String originStationId;
    @Column(name = "station_name", nullable = false)
    private String stationName;
    @Column(name = "latitude", nullable = false, precision = 10, scale = 7)
    private BigDecimal latitude;
    @Column(name = "longitude", nullable = false, precision = 10, scale = 7)
    private BigDecimal longitude;
    @Column(name = "collection_date", nullable = false)
    private LocalDate collectionDate;
    @Column(name = "ars_id")
    private String arsId;
    @Column(name = "city_code")
    private String cityCode;
    @Column(name = "city_name")
    private String cityName;
    @Column(name = "management_city_name")
    private String managementCityName;
    @Column(name = "address")
    private String address;

    protected BusStation() {
    }

}
