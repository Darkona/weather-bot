package com.darkona.weather.weatherbot.response;

import com.darkona.weather.weatherbot.util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;


@Data
public class WeatherConditions {

    @JsonIgnore
    private Double temperature;

    @JsonIgnore
    private boolean timeEnabled;

    @JsonIgnore
    private LocalDateTime dateTime;

    @JsonProperty("weather")
    private String weatherType;

    @JsonIgnore
    private String unit;

    @JsonIgnore
    private Double humidity;

    @JsonIgnore
    private ZoneId timezone;
    @JsonIgnore
    private Double highTemp;
    @JsonIgnore
    private Double lowTemp;

    @JsonProperty("humidity")
    public String getHumidity(){
        return Util.formatNoDecimals(humidity) + "%";
    }

    @JsonProperty("temperature")
    public String getTemperature(){
        return temperature != null ? Util.formatTwoDecimals(temperature) + unit : null;
    }

    @JsonProperty("date")
    public String getFormattedDate(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dateTime.format(formatter);
    }

    @JsonProperty("time")
    public String getFormattedTime(){
        if (isTimeEnabled()) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("hh:mm a");
            return dateTime.format(formatter);
        }
        return null;
    }

    @JsonProperty("highTemp")
    public String getHighTemp() {
        return highTemp != null ? Util.formatTwoDecimals(highTemp) + getUnit() : null;
    }

    @JsonProperty("lowTemp")
    public String getLowTemp() {
        return lowTemp != null ? Util.formatTwoDecimals(lowTemp) + getUnit() : null;
    }


}
