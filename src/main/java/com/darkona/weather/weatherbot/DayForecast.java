package com.darkona.weather.weatherbot;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class DayForecast {

    @JsonProperty("highTemp")
    private double highTemp;
    @JsonProperty("lowtemp")
    private double lowTemp;
    private LocalDateTime dateTime;
    private boolean withTime;
    @JsonProperty("weather")
    private String weatherType;

    @JsonProperty("date")
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }

    @JsonProperty("time")
    public String getFormattedTime(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
        return dateTime.format(formatter);
    }


}
