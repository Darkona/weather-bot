package com.darkona.weather.weatherbot.response;

import com.darkona.weather.weatherbot.controller.HomeController;
import com.darkona.weather.weatherbot.controller.WeatherController;
import lombok.Data;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@Data
public class ResourceHelp extends ResourceSupport {
    public static final String currentWeather = "weather/currentWeather";
    public static final String pastWeek = "weather/pastWeek";

    public ResourceHelp() {
        add(linkTo(WeatherController.class).withRel("weather"));
        add(linkTo(HomeController.class).withSelfRel());
        add(linkTo(methodOn(WeatherController.class).currentWeather("Santiago", "C")).withRel("currentWeatherInSantiagoChile"));
        add(linkTo(methodOn(WeatherController.class).pastWeek("New York", "F")).withRel("pastWeekWeatherInNewYork"));
    }
}
