package com.darkona.weather.weatherbot.service;

import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.response.WeatherConditions;

import java.util.ArrayList;

public interface WeatherService {

    WeatherConditions getWeatherNow(WeatherRequest request);

    ArrayList<WeatherConditions> getPastWeek(WeatherRequest request) throws Exception;

}
