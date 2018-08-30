package com.darkona.weather.weatherbot;

import com.darkona.weather.weatherbot.DTO.City;
import com.darkona.weather.weatherbot.Service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherbotEndpoint {


    @Autowired
    private WeatherService service;

    @GetMapping("/todayForecast")
    @ResponseBody
    public DayForecast todayWeather(@RequestParam(name="city") String city, @RequestParam(name="unit") String unit) {

        return service.getWeatherNow(City.getLondon());
    }

    @GetMapping("/weekForecast")
    @ResponseBody
    public List<DayForecast> weekForecast(@RequestParam(name="city") String city, @RequestParam(name="unit") String unit) {
        ArrayList<DayForecast> forecasts = new ArrayList<>();
        for (int c = 0; c <= 6; c++){
            DayForecast forecast = new DayForecast();
            forecast.setHighTemp(Math.random());
            forecast.setLowTemp(Math.random());
            forecasts.add(forecast);
        }
        return forecasts;
    }


}
