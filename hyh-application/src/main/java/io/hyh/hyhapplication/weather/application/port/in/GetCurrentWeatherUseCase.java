package io.hyh.hyhapplication.weather.application.port.in;

import io.hyh.hyhapplication.weather.application.port.in.dto.GetCurrentWeatherCommand;
import io.hyh.hyhapplication.weather.domain.CurrentWeather;
import org.jetbrains.annotations.NotNull;

public interface GetCurrentWeatherUseCase {

    @NotNull CurrentWeather getCurrentWeather(@NotNull GetCurrentWeatherCommand command);

}
