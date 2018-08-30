package com.darkona.weather.weatherbot.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.io.Serializable;
import java.time.Instant;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DataPointObjectDTO implements Serializable {


    private Instant time;
    private String icon;
    private Double humidity;

    public void setTime(long time){
        this.time = Instant.ofEpochSecond(time);
    }
}
