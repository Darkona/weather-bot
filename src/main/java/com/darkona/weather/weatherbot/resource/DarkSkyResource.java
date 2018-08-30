package com.darkona.weather.weatherbot.resource;

import com.darkona.weather.weatherbot.domain.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;


@Service
public class DarkSkyResource {

    private static Logger logger = LoggerFactory.getLogger(DarkSkyResource.class);

    public ResponseEntity<DarkSkyForecastDTO> getWeatherFromDarksky(WeatherRequest request, String excluded) {

        RestTemplate restTemplate = new RestTemplate();
        String unixTime = Long.toString(request.getTime().getEpochSecond());
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        String fullURI = Constants.API_URI + "/" + Constants.KEY + "/";
        String parameters = request.getCity().getLatitude() + "," + request.getCity().getLongitude() + "," + unixTime;
        parameters += "?excluded=" + excluded + "&units=" + request.getUnitName();

        logger.info("Using parameters>>> " + parameters);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        return restTemplate.exchange(fullURI + parameters, HttpMethod.GET, entity, DarkSkyForecastDTO.class);
    }
}
