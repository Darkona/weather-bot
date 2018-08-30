package com.darkona.weather.weatherbot;

import com.darkona.weather.weatherbot.response.WeatherConditions;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;

import static org.junit.Assert.assertEquals;

public class DayWeatherConditionsTest {

    private LocalDateTime dateTime;
    private WeatherConditions dayForecast;

    @Before
    public void setup(){
        dateTime = LocalDateTime.of(2018,8,29,15,20);
        dayForecast = new WeatherConditions();
        dayForecast.setTemperature(23d);
        dayForecast.setDateTime(dateTime);
    }

    @Test
    public void getFormattedDate() {
        String output = dayForecast.getFormattedDate();
        assertEquals("29/08/2018", output);
    }

    @Test
    public void getFormattedDateTime() {
        String output = dayForecast.getFormattedTime();
        assertEquals("03:20 PM", output);
    }
}