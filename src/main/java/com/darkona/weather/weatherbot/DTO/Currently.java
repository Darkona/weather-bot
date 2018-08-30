package com.darkona.weather.weatherbot.DTO;

import lombok.Data;

@Data
public class Currently extends DataPointObjectDTO {

    private Double nearestStormBearing;
    private Double nearestStormDistance;

}
