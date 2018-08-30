package com.darkona.weather.weatherbot.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
public class DailyDTO extends DataPointObjectDTO {

    private List<CurrentlyDTO> data;
}
