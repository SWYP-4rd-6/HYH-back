package io.hyh.hyhapplication.transit.application.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CityType {
    SEOUL(11),
    Gyeonggi_Do(1);

    private final int code;
}
