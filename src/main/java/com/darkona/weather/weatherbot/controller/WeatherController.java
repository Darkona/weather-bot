package com.darkona.weather.weatherbot.controller;

import com.darkona.weather.weatherbot.exception.BadUnitException;
import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.response.WeatherConditions;
import com.darkona.weather.weatherbot.service.WeatherService;
import com.darkona.weather.weatherbot.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/weather", produces = "application/hal+json")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping("/currentWeather")
    @ResponseBody
    public ResponseEntity<WeatherConditions> currentWeather(@RequestParam(name = "city") String city, @RequestParam(name = "unit", required = false, defaultValue = "C") String unit) {
        WeatherConditions conditions;
        try {
            conditions = weatherService.getWeatherNow(new WeatherRequest(city, unit));
            return new ResponseEntity<>(conditions, HttpStatus.OK);
        } catch (BadUnitException e) {
            conditions = new WeatherConditions();
            conditions.setError(Constants.WRONG_UNIT);
            return new ResponseEntity<>(conditions, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/pastWeek")
    @ResponseBody
    public ResponseEntity<List<WeatherConditions>> pastWeek(@RequestParam(name = "city") String city, @RequestParam(name = "unit") String unit) {
        List<WeatherConditions> conditions;
        try {
            conditions = weatherService.getPastWeek(new WeatherRequest(city, unit));
        } catch (BadUnitException e) {
            conditions = new ArrayList<>();
            WeatherConditions c = new WeatherConditions();
            c.setError(Constants.WRONG_UNIT);
            conditions.add(c);
        }
        return new ResponseEntity<>(conditions, HttpStatus.BAD_REQUEST);
    }


}
