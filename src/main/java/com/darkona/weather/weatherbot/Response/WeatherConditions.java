package com.darkona.weather.weatherbot.Response;

import com.darkona.weather.weatherbot.Util.Constants;
import com.darkona.weather.weatherbot.Util.Util;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Data
public class WeatherConditions {

    @JsonIgnore
    private Double temperature;

    @JsonIgnore
    private LocalDateTime dateTime;

    @JsonProperty("weather")
    private String weatherType;

    @JsonIgnore
    private String unit;

    @JsonIgnore
    private Double humidity;

    @JsonIgnore
    private Double pressure;

    @JsonProperty("humidity")
    public String getHumidity(){
        return Util.formatTwoDecimals(humidity) + "%";
    }

    @JsonProperty("temperature")
    public String getTemperature(){
        return Util.formatTwoDecimals(temperature) + unit;
    }

    @JsonProperty("Pressure")
    public String getPressure(){
        return Util.formatFourDecimals(pressure) + Constants.MILLIBARS;
    }

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
