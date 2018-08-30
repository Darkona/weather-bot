package com.darkona.weather.weatherbot.service;

import com.darkona.weather.weatherbot.exception.BadUnitException;
import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.response.WeatherConditions;

import java.util.List;

public interface WeatherService {

    WeatherConditions getWeatherNow(WeatherRequest request) throws BadUnitException;

    List<WeatherConditions> getPastWeek(WeatherRequest request);

}
