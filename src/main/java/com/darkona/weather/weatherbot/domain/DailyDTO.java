package com.darkona.weather.weatherbot.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class DailyDTO extends DataPointObjectDTO {

    public ArrayList<CurrentlyDTO> data;
}
