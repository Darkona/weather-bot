package com.darkona.weather.weatherbot.service.Impl;

import com.darkona.weather.weatherbot.domain.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.response.WeatherConditions;
import com.darkona.weather.weatherbot.service.ConversionService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class ConversionServiceImpl implements ConversionService {

    @Override
    public WeatherConditions convertDarkskyToDayForecast(DarkSkyForecastDTO input, WeatherRequest request) {
        WeatherConditions forecast = new WeatherConditions();

        forecast.setDateTime(LocalDateTime.ofInstant(input.getCurrently().getTime(), input.getTimezone()));
        forecast.setHumidity(input.getCurrently().getHumidity() * 100);
        forecast.setWeatherType(input.getCurrently().getIcon());
        forecast.setTimezone(input.getTimezone());
        forecast.setTemperature(input.getCurrently().getTemperature());
        forecast.setUnit("°" + request.getUnit());
        return forecast;
    }

    @Override
    public WeatherConditions convertDarkSkyToWeeklyForecast(DarkSkyForecastDTO input, WeatherRequest request) {
        WeatherConditions forecast = convertDarkskyToDayForecast(input, request);
        forecast.setHighTemp(input.getDaily().getData().get(0).getTemperatureHigh());
        forecast.setLowTemp(input.getDaily().getData().get(0).getTemperatureLow());
        forecast.setTemperature(null);
        forecast.setTimeEnabled(false);
        return forecast;
    }
}

