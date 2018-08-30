package com.darkona.weather.weatherbot.Service;

import com.darkona.weather.weatherbot.DTO.City;
import com.darkona.weather.weatherbot.DTO.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.DayForecast;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;


public class WeatherService {

    @Autowired
    private ConversionService converter;

    public DayForecast getWeatherNow(City city)
    {
        final String key = "72d62a9144378956e23f2c5901e0445c";
        final String exclude = "exclude=minutely,hourly,flags";
        final String uri = "https://api.darksky.net/forecast/" + key + "/";

        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        String parameters = city.getLatitude() + "," + city.getLongitude() + "&" + exclude;
        //String parameters = "London";
        HttpEntity<String> entity = new HttpEntity<>(parameters, headers);

        ResponseEntity<DarkSkyForecastDTO> response = restTemplate.getForEntity(uri, DarkSkyForecastDTO.class);

        return converter.getDayForecast(response.getBody());

    }
}
