package com.darkona.weather.weatherbot.Service;

import com.darkona.weather.weatherbot.DTO.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.DayForecast;
import com.darkona.weather.weatherbot.Util.Util;
import org.springframework.stereotype.Service;

@Service
public class ConversionService {

    public DayForecast getDayForecast(DarkSkyForecastDTO input){
        DayForecast forecast = new DayForecast();
        forecast.setHighTemp(Util.FahrenheitToKelvin(input.getCurrently().getApparentTemperatureHigh()));
        forecast.setLowTemp(Util.FahrenheitToKelvin(input.getCurrently().getApparentTemperatureLow()));
        return forecast;
    }
}
