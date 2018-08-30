package com.darkona.weather.weatherbot.service.impl;

import com.darkona.weather.weatherbot.domain.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.exception.BadUnitException;
import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.resource.DarkSkyResource;
import com.darkona.weather.weatherbot.response.WeatherConditions;
import com.darkona.weather.weatherbot.service.ConversionService;
import com.darkona.weather.weatherbot.service.ValidationService;
import com.darkona.weather.weatherbot.service.WeatherService;
import com.darkona.weather.weatherbot.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class WeatherServiceImpl implements WeatherService {

    @Autowired
    private ConversionService converter;

    @Autowired
    private ValidationService validationService;

    @Autowired
    private DarkSkyResource darkSkyResource;

    @Override
    public WeatherConditions getWeatherNow(WeatherRequest request) throws BadUnitException {
        WeatherConditions conditions;
        if (!validationService.isValidUnit(request.getUnit())) {
            throw new BadUnitException();
        } else {
            Instant now = Instant.now();
            request.setTime(now);
            conditions = getWeatherAtTime(request);
        }
        return conditions;
    }


    @Override
    public List<WeatherConditions> getPastWeek(WeatherRequest request) {

        if (!validationService.isValidUnit(request.getUnit())) {
            throw new BadUnitException();
        } else {
            ArrayList<WeatherConditions> pastWeek = new ArrayList<>();
            Instant now = Instant.now();
            request.setTime(now);
            WeatherConditions todayWeather = getWeatherOfDay(request);
            pastWeek.add(todayWeather);

            LocalDateTime today = LocalDateTime.ofInstant(now, todayWeather.getTimezone());

            for (int i = 6; i > 0; i--) {
                today = today.minusDays(1);
                request.setTime(today.toInstant(ZoneOffset.UTC));
                WeatherConditions results = getWeatherOfDay(request);
                pastWeek.add(results);
            }
            return pastWeek;
        }
    }

    private WeatherConditions getWeatherOfDay(WeatherRequest request) {
        request.setUnitName(request.getUnit().equals("C") ? Constants.CELSIUS_UNIT_NAME : Constants.FAHRENHEIT_UNIT_NAME);
        request.setCity(CitiesService.getCityByName(request.getCityName()));
        ResponseEntity<DarkSkyForecastDTO> entity = darkSkyResource.getWeatherFromDarksky(request, Constants.EXCLUDE_FOR_WEEK);
        return converter.convertDarkSkyToWeeklyForecast(entity.getBody(), request);
    }

    private WeatherConditions getWeatherAtTime(WeatherRequest request) {
        request.setUnitName(request.getUnit().equals("C") ? Constants.CELSIUS_UNIT_NAME : Constants.FAHRENHEIT_UNIT_NAME);
        request.setCity(CitiesService.getCityByName(request.getCityName()));
        ResponseEntity<DarkSkyForecastDTO> entity = darkSkyResource.getWeatherFromDarksky(request, Constants.EXCLUDE_FOR_CURRENT);
        return converter.convertDarkskyToDayForecast(entity.getBody(), request);
    }
}
