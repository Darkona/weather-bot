package com.darkona.weather.weatherbot.DTO;


import lombok.Data;

@Data
public class DarkSkyForecastDTO {

    String latitude;
    String longitude;
    String timezone;
    DataPointObjectDTO currently;
    Object daily;
    Object alerts;

}
