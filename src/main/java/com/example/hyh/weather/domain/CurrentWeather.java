package com.example.hyh.weather.domain;

public record CurrentWeather(
        WeatherCondition weatherCondition,
        HumidityLevel humidityLevel,
        RainfallIntensity rainfallIntensity,
        AirQuality.Pm10 airQualityPm10,
        AirQuality.Pm25 airQualityPm25,
        UvIndex uvIndex
) {
}
