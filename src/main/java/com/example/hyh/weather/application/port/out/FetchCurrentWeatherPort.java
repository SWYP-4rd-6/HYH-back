package com.example.hyh.weather.application.port.out;

import com.example.hyh.weather.domain.CurrentWeather;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FetchCurrentWeatherPort {

    @NotNull CurrentWeather fetchCurrentWeather(@NotNull String depth1,
                                                @Nullable String depth2,
                                                @Nullable String depth3);

}
