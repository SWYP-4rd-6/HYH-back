package io.hyh.hyhapplication.weather.application.port.out;

import io.hyh.hyhapplication.weather.domain.CurrentWeather;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface FetchCurrentWeatherPort {

    @NotNull CurrentWeather fetchCurrentWeather(@NotNull String depth1,
                                                @Nullable String depth2,
                                                @Nullable String depth3);

}
