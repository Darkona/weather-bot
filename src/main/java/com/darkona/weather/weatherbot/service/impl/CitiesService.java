package com.darkona.weather.weatherbot.service.impl;

import com.darkona.weather.weatherbot.domain.City;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
public class CitiesService {


    private CitiesService() {
    }

    public static City getCityByName(String name) {
        City city = new City();
        String[] coords = cityList.get(name).split(",");
        city.setName(name);
        city.setLatitude(coords[0].trim());
        city.setLongitude(coords[1].trim());
        return city;
    }

    private static HashMap<String,String> cityList = new HashMap<>();

    static {
        cityList.put("Santiago", "-33.448891,-70.669266");
        cityList.put("London", "51.507351, -0.127758");
        cityList.put("New York", "40.712776, -74.005974");
        cityList.put("Paris", "48.856613, 2.352222");
        cityList.put("Shangai", "31.230391, 121.473701");
        cityList.put("Tokio", "35.689487,139.691711");
    }
}
