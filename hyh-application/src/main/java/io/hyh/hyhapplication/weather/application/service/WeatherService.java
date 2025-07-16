package io.hyh.hyhapplication.weather.application.service;

import io.hyh.hyhapplication.weather.application.port.in.GetCurrentWeatherUseCase;
import io.hyh.hyhapplication.weather.application.port.in.dto.GetCurrentWeatherCommand;
import io.hyh.hyhapplication.weather.application.port.out.FetchCurrentWeatherPort;
import io.hyh.hyhapplication.weather.domain.CurrentWeather;
import lombok.RequiredArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
class WeatherService implements GetCurrentWeatherUseCase {

    private final FetchCurrentWeatherPort fetchCurrentWeatherPort;

    @Override
    public @NotNull CurrentWeather getCurrentWeather(@NotNull GetCurrentWeatherCommand command) {
        return fetchCurrentWeatherPort.fetchCurrentWeather(command.depth1(), command.depth2(), command.depth3());
    }

}
