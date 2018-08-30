package com.darkona.weather.weatherbot.Service;

import com.darkona.weather.weatherbot.DTO.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.Response.WeatherConditions;

public interface ConversionService {

    public WeatherConditions convertDarkskyToDayForecast(DarkSkyForecastDTO darkSkyForecastDTO);

}
