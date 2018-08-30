package com.darkona.weather.weatherbot.service;

import com.darkona.weather.weatherbot.domain.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.response.WeatherConditions;

public interface ConversionService {

    WeatherConditions convertDarkskyToDayForecast(DarkSkyForecastDTO input, WeatherRequest request);

    WeatherConditions convertDarkSkyToWeeklyForecast(DarkSkyForecastDTO input, WeatherRequest request);
}
