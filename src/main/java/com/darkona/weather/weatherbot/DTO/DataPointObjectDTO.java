package com.darkona.weather.weatherbot.DTO;

import lombok.Data;

@Data
public class DataPointObjectDTO {

    private double apparentTemperatureHigh;
    private double apparentTemperatureLow;
    private double humidity;
    private double windSpeed;
}
