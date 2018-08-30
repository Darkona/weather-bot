package com.darkona.weather.weatherbot.request;

import com.darkona.weather.weatherbot.domain.CityDTO;
import lombok.Data;

import java.time.Instant;

@Data

public class WeatherRequest {
    private String cityName;

    private Instant time;

    private CityDTO city;
    private String unit;
    private String unitName;

    public WeatherRequest() {
    }

    public WeatherRequest(String cityName, String unit) {
        this.cityName = cityName;
        this.unit = unit;
    }

}
