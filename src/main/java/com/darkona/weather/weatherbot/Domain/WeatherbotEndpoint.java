package com.darkona.weather.weatherbot.Domain;

import com.darkona.weather.weatherbot.Service.Impl.Cities;
import com.darkona.weather.weatherbot.Response.WeatherConditions;
import com.darkona.weather.weatherbot.Service.Impl.WeatherServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class WeatherbotEndpoint {

    /*This is a hardcoded "CityDTO service" which would get coordinates from another API or database given the city name*/
    private Cities cities = Cities.getInstance();

    @Autowired
    private WeatherServiceImpl weatherService;



    @GetMapping("/weatherNow")
    @ResponseBody
    public WeatherConditions todayWeather(@RequestParam(name="city") String city, @RequestParam(name="unit", required=false, defaultValue = "C") String unit) {
            return weatherService.getWeatherNow(Cities.getCityByName(city), unit);
    }

    @GetMapping("/weekForecast")
    @ResponseBody
    public List<WeatherConditions> weekForecast(@RequestParam(name="city") String city, @RequestParam(name="unit") String unit) {
        ArrayList<WeatherConditions> forecasts = new ArrayList<>();
        for (int c = 0; c <= 6; c++){
            WeatherConditions forecast = new WeatherConditions();
            forecast.setTemperature(Math.random());
            forecasts.add(forecast);
        }
        return forecasts;
    }


}
