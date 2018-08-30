package com.darkona.weather.weatherbot.service;

import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.response.WeatherConditions;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;

public interface WeatherService {

    ResponseEntity getWeatherNow(WeatherRequest request);

    ArrayList<WeatherConditions> getPastWeek(WeatherRequest request) throws Exception;

}
