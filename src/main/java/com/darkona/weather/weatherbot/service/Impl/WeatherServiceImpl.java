package com.darkona.weather.weatherbot.service.Impl;

import com.darkona.weather.weatherbot.domain.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.resource.DarkSkyResource;
import com.darkona.weather.weatherbot.response.WeatherConditions;
import com.darkona.weather.weatherbot.service.WeatherService;
import com.darkona.weather.weatherbot.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private ConversionServiceImpl converter;
    @Autowired
    private ValidationServiceImpl validationService;
    @Autowired
    private DarkSkyResource darkSkyResource;


    public WeatherConditions getWeatherNow(WeatherRequest request) {
        Instant now = Instant.now();
        request.setTime(now);
        return getWeatherAtTime(request, Constants.EXCLUDE_FOR_CURRENT);
    }

    private WeatherConditions getWeatherOfDay(WeatherRequest request, String exclusion) {
        if (validationService.isValidUnit(request.getUnit())) {
            //If its invalid, just use Celsius, dont want to keep making it more difficult for now
            request.setUnit(Constants.CELSIUS);
        }
        request.setUnitName(request.getUnit().equals("C") ? Constants.CELSIUS_UNIT_NAME : Constants.FAHRENHEIT_UNIT_NAME);
        request.setCity(CitiesService.getCityByName(request.getCityName()));
        ResponseEntity<DarkSkyForecastDTO> entity = darkSkyResource.getWeatherFromDarksky(request, exclusion);
        return converter.convertDarkSkyToWeeklyForecast(entity.getBody(), request);
    }

    @Override
    public ArrayList<WeatherConditions> getPastWeek(WeatherRequest request) {
        if (validationService.isValidUnit(request.getUnit())) {
            //If its invalid, just use Celsius, dont want to keep making it more difficult for now
            request.setUnit(Constants.CELSIUS);
        }

        //Make past week
        ArrayList<WeatherConditions> pastWeek = new ArrayList<>();
        Instant now = Instant.now();
        request.setTime(now);
        WeatherConditions todayWeather = getWeatherOfDay(request, Constants.EXCLUDE_FOR_WEEK);
        pastWeek.add(todayWeather);

        LocalDateTime today = LocalDateTime.ofInstant(now, todayWeather.getTimezone());

        for (int i = 6; i > 0; i--) {
            today = today.minusDays(1);
            request.setTime(today.toInstant(ZoneOffset.UTC));
            WeatherConditions results = getWeatherOfDay(request, Constants.EXCLUDE_FOR_WEEK);
            pastWeek.add(results);
        }

        return pastWeek;
    }


    private WeatherConditions getWeatherAtTime(WeatherRequest request, String exclusion) {

        if (validationService.isValidUnit(request.getUnit())) {
            //If its invalid, just use Celsius, dont want to keep making it more difficult for now
            request.setUnit(Constants.CELSIUS);
        }
        request.setUnitName(request.getUnit().equals("C") ? Constants.CELSIUS_UNIT_NAME : Constants.FAHRENHEIT_UNIT_NAME);
        request.setCity(CitiesService.getCityByName(request.getCityName()));
        ResponseEntity<DarkSkyForecastDTO> entity = darkSkyResource.getWeatherFromDarksky(request, exclusion);
        return converter.convertDarkskyToDayForecast(entity.getBody(), request);
    }
}
