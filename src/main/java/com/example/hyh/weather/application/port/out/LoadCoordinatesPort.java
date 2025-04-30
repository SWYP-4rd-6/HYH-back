package com.example.hyh.weather.application.port.out;

import com.example.hyh.weather.domain.AdministrativeRegion;

public interface LoadCoordinatesPort {

    AdministrativeRegion getCoordinatesForAddress(String depth1, String depth2, String depth3);

}
