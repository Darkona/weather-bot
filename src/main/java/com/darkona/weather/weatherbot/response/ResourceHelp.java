package com.darkona.weather.weatherbot.response;

import com.darkona.weather.weatherbot.controller.HomeController;
import com.darkona.weather.weatherbot.controller.WeatherController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class ResourceHelp extends ResourceSupport {
    public String currentWeather = "weather/currentWeather";
    public String pastWeek = "weather/pastWeek";

    public ResourceHelp() throws Exception {
        add(linkTo(WeatherController.class).withRel("weather"));
        add(linkTo(HomeController.class).withSelfRel());
        add(linkTo(methodOn(WeatherController.class).currentWeather("Santiago", "C")).withRel("currentWeatherInSantiagoChile"));
        add(linkTo(methodOn(WeatherController.class).pastWeek("New York", "F")).withRel("pastWeekWeatherInNewYork"));
    }
}
