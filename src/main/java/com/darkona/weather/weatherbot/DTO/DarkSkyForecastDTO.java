package com.darkona.weather.weatherbot.DTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.time.ZoneId;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class DarkSkyForecastDTO {

    String latitude;
    String longitude;
    ZoneId timezone;
    Currently currently;

    public void setTimezone(String timezone){
        this.timezone = ZoneId.of(timezone);
    }

}
