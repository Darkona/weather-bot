package com.darkona.weather.weatherbot.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CurrentlyDTO extends DataPointObjectDTO {

    private Double temperature;
    private Double temperatureHigh;
    private Double temperatureLow;

}
