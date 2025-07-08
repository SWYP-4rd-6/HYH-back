package io.hyh.hyhapplication.weather.application.port.in;

import io.hyh.hyhapplication.weather.application.port.in.dto.GetTodayWeatherCommand;
import io.hyh.hyhapplication.weather.application.port.in.dto.GetTodayWeatherResult;
import org.jetbrains.annotations.NotNull;

public interface GetTodayWeatherUseCase {

    @NotNull GetTodayWeatherResult getTodayWeather(@NotNull GetTodayWeatherCommand request);

}
