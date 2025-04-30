package com.example.hyh.weather.application.port.in;

import com.example.hyh.weather.domain.CurrentWeather;
import org.jetbrains.annotations.NotNull;

public interface GetCurrentWeatherUseCase {

    @NotNull CurrentWeather getCurrentWeather(@NotNull GetCurrentWeatherCommand command);

}