package com.darkona.weather.weatherbot.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPointObjectDTO implements Serializable {


    private Instant time;
    private String summary;
    private String icon;
    //private Double precipIntensity;
    private Double precipProbability;
    private String precipType;
    private Double temperature;
    private Double apparentTemperature;
    //private Double dewPoint;
    private Double humidity;
    private Double windSpeed;
    //private Integer windBearing;
    private Double pressure;
    //private Double cloudCover;
    //private Double ozone;
    //private Double visibility;
    private double apparentTemperatureHigh;
    private double apparentTemperatureLow;

    public void setTime(long time){
        this.time = Instant.ofEpochSecond(time);
    }
}
