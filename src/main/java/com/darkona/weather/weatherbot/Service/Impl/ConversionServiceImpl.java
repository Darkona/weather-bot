package com.darkona.weather.weatherbot.Service.Impl;

import com.darkona.weather.weatherbot.DTO.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.Response.WeatherConditions;
import com.darkona.weather.weatherbot.Util.Constants;
import com.darkona.weather.weatherbot.Util.Util;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversionServiceImpl {

    public WeatherConditions convertDarkskyToDayForecast(DarkSkyForecastDTO input, String unit){
        WeatherConditions forecast = new WeatherConditions();

        forecast.setDateTime(LocalDateTime.ofInstant(input.getCurrently().getTime(), input.getTimezone()));
        forecast.setPressure(input.getCurrently().getPressure());
        forecast.setHumidity(input.getCurrently().getHumidity());
        forecast.setWeatherType(input.getCurrently().getIcon());

        //Set temperatures depending on unit
        if(unit.equalsIgnoreCase(Constants.FAHRENHEIT)){
            forecast.setTemperature(input.getCurrently().getTemperature());
        }
        else {
            forecast.setTemperature(Util.FahrenheitToCelsius(input.getCurrently().getTemperature()));
        }
        forecast.setUnit("°"+unit);
        return forecast;
    }
}
