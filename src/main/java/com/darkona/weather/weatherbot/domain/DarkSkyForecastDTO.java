package com.darkona.weather.weatherbot.domain;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.ZoneId;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkSkyForecastDTO {

    String latitude;
    String longitude;
    ZoneId timezone;
    CurrentlyDTO currently;
    DailyDTO daily;
    public void setTimezone(String timezone){
        this.timezone = ZoneId.of(timezone);
    }

}
