package io.hyh.hyhapplication.weather.domain;

import lombok.Getter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Set;

public enum WeatherCondition {
    ClearDay("맑음(낮)"),
    ClearNight("맑음(밤)"),
    PartlyCloudyDay("구름(낮)"),
    PartlyCloudyNight("구름(밤)"),
    Cloudy("흐림"),
    Rain("비"),
    Snow("눈"),
    Thunderstorm("천둥번개"),
    Invalid("-");

    private final static Set<String> RAIN_CONDITIONS = Set.of("1", "4", "5");
    private final static Set<String> SNOW_CONDITIONS = Set.of("3", "7");


    @Getter
    private final String displayName;

    WeatherCondition(String displayName) {
        this.displayName = displayName;
    }

    public static @NotNull WeatherCondition from(@Nullable String sky,
                                                 @Nullable String pty,
                                                 @Nullable String lightning) {
        if ("1".equals(sky) && "0".equals(pty)) {
            return WeatherCondition.ClearDay;
        } else if ("3".equals(sky) && "0".equals(pty)) {
            return WeatherCondition.PartlyCloudyDay;
        } else if ("4".equals(sky) && "0".equals(pty)) {
            return WeatherCondition.Cloudy;
        } else if (RAIN_CONDITIONS.contains(pty)) {
            return WeatherCondition.Rain;
        } else if (SNOW_CONDITIONS.contains(pty)) {
            return WeatherCondition.Snow;
        } else if ("1".equals(lightning)) {
            return WeatherCondition.Thunderstorm;
        }
        return WeatherCondition.Invalid;
    }

}
