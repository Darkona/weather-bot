package com.darkona.weather.weatherbot.Service.Impl;

import com.darkona.weather.weatherbot.DTO.CityDTO;
import com.darkona.weather.weatherbot.DTO.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.Response.WeatherConditions;
import com.darkona.weather.weatherbot.Service.ValidationService;
import com.darkona.weather.weatherbot.Service.WeatherService;
import com.darkona.weather.weatherbot.Util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Collections;

@Service
public class WeatherServiceImpl implements WeatherService {

    private static Logger logger = LoggerFactory.getLogger(WeatherService.class);

    @Autowired
    private ConversionServiceImpl converter;
    @Autowired
    private ValidationService validationService;

    public WeatherConditions getWeatherNow(CityDTO city, String unit) {
        if(!validationService.isValidUnit(unit)) {
            //If its invalid, just use Celsius, dont want to keep making it more difficult for now
            unit = Constants.CELSIUS;
        }

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.add("excluded",Constants.EXCLUDE_FOR_CURRENT);
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String fullURI = Constants.API_URI + "/" + Constants.KEY + "/";
        String parameters = city.getLatitude() + "," + city.getLongitude();
        HttpEntity<String> entity = new HttpEntity<>(headers);
        ResponseEntity<DarkSkyForecastDTO> response = restTemplate.exchange(fullURI + parameters, HttpMethod.GET, entity, DarkSkyForecastDTO.class);

        return converter.convertDarkskyToDayForecast(response.getBody(), unit);

    }

    @Override
    public ArrayList<WeatherConditions> getWeekForecast(CityDTO city, String unit) {

        return null;
    }

}
