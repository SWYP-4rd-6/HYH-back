package io.hyh.hyhapplication.weather.application.port.out;

import io.hyh.hyhapplication.weather.domain.AdministrativeRegion;

public interface LoadCoordinatesPort {

    AdministrativeRegion getCoordinatesForAddress(String depth1, String depth2, String depth3);

}
