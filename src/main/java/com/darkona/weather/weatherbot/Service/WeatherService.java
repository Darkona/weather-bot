package com.darkona.weather.weatherbot.Service;

import com.darkona.weather.weatherbot.DTO.CityDTO;
import com.darkona.weather.weatherbot.Response.WeatherConditions;

import java.util.ArrayList;

public interface WeatherService {

    WeatherConditions getWeatherNow(CityDTO city, String unit);

    ArrayList<WeatherConditions> getWeekForecast(CityDTO city, String Unit);

}
