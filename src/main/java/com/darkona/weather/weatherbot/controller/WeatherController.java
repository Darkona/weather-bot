package com.darkona.weather.weatherbot.controller;

import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.response.WeatherConditions;
import com.darkona.weather.weatherbot.service.Impl.CitiesService;
import com.darkona.weather.weatherbot.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@RequestMapping(value = "/weather", produces = "application/hal+json")
public class WeatherController {

    /*This is a hardcoded "CityDTO service" which would get coordinates from another API or database given the city name*/
    private CitiesService cities = CitiesService.getInstance();

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/currentWeather")
    @ResponseBody
    public WeatherConditions currentWeather(@RequestParam(name = "city") String city, @RequestParam(name = "unit", required = false, defaultValue = "C") String unit) {
        return weatherService.getWeatherNow(new WeatherRequest(city, unit));
    }

    @GetMapping("/pastWeek")
    @ResponseBody
    public ArrayList<WeatherConditions> pastWeek(@RequestParam(name = "city") String city, @RequestParam(name = "unit") String unit) throws Exception {
        return weatherService.getPastWeek(new WeatherRequest(city, unit));
    }


}
