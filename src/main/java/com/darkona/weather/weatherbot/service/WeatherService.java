package com.darkona.weather.weatherbot.service;

import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.response.WeatherConditions;

import java.util.List;

public interface WeatherService {

    WeatherConditions getWeatherNow(WeatherRequest request);

    List<WeatherConditions> getPastWeek(WeatherRequest request);

}
