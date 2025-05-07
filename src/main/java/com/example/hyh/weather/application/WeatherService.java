package com.example.hyh.weather.application;

import com.example.hyh.weather.application.port.in.GetCurrentWeatherCommand;
import com.example.hyh.weather.application.port.in.GetCurrentWeatherUseCase;
import com.example.hyh.weather.application.port.out.FetchCurrentWeatherPort;
import com.example.hyh.weather.domain.CurrentWeather;
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
