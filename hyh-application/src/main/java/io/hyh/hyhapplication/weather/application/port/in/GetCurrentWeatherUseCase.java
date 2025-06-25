package io.hyh.hyhapplication.weather.application.port.in;

import org.jetbrains.annotations.NotNull;

import io.hyh.hyhapplication.weather.domain.CurrentWeather;

public interface GetCurrentWeatherUseCase {

    @NotNull CurrentWeather getCurrentWeather(@NotNull GetCurrentWeatherCommand command);

}
