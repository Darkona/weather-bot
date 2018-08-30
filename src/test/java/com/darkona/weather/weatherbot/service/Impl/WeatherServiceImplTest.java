package com.darkona.weather.weatherbot.service.Impl;

import com.darkona.weather.weatherbot.domain.City;
import com.darkona.weather.weatherbot.domain.CurrentlyDTO;
import com.darkona.weather.weatherbot.domain.DailyDTO;
import com.darkona.weather.weatherbot.domain.DarkSkyForecastDTO;
import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.resource.DarkSkyResource;
import com.darkona.weather.weatherbot.response.WeatherConditions;
import com.darkona.weather.weatherbot.util.Constants;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;

import static org.junit.Assert.*;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

//@RunWith(SpringRunner.class)
@RunWith(MockitoJUnitRunner.class)
public class WeatherServiceImplTest {

    @Mock
    DarkSkyResource darkSkyResource;

    @Spy
    ValidationServiceImpl validationService;
    @Spy
    ConversionServiceImpl conversionService;

    @InjectMocks
    WeatherServiceImpl weatherService;

    private DarkSkyForecastDTO forecastCelsius;
    private DarkSkyForecastDTO forecastFahrenheit;
    private ResponseEntity<DarkSkyForecastDTO> responseEntityC;
    private ResponseEntity<DarkSkyForecastDTO> responseEntityF;
    private Instant now;


    @Before
    public void setup() {
        weatherService = new WeatherServiceImpl();
        MockitoAnnotations.initMocks(this);
        String zone = "Europe/London";
        now = Instant.now();

        LocalDateTime today = LocalDateTime.ofInstant(now, ZoneId.of("Europe/London"));
        City london = CitiesService.getCityByName("London");

        forecastFahrenheit = new DarkSkyForecastDTO();
        forecastFahrenheit.setLatitude(london.getLatitude());
        forecastFahrenheit.setLongitude(london.getLongitude());
        forecastFahrenheit.setTimezone(zone);

        CurrentlyDTO currentlyDTOF = new CurrentlyDTO();
        currentlyDTOF.setTemperature(59d);
        currentlyDTOF.setIcon("cloudy-day");
        currentlyDTOF.setHumidity(.25);
        currentlyDTOF.setTime(now.getEpochSecond());
        forecastFahrenheit.setCurrently(currentlyDTOF);


        forecastCelsius = new DarkSkyForecastDTO();
        forecastCelsius.setLatitude(london.getLatitude());
        forecastCelsius.setLongitude(london.getLongitude());
        forecastCelsius.setTimezone(zone);


        CurrentlyDTO currentlyDTOC = new CurrentlyDTO();
        currentlyDTOC.setTemperature(15d);
        currentlyDTOC.setIcon("cloudy-day");
        currentlyDTOC.setHumidity(.25);
        currentlyDTOC.setTime(now.getEpochSecond());
        forecastCelsius.setCurrently(currentlyDTOC);

        DailyDTO dailyDTOC = new DailyDTO();
        CurrentlyDTO dailyCurrently = new CurrentlyDTO();
        dailyCurrently.setTemperatureHigh(20.0d);
        dailyCurrently.setTemperatureLow(10.0d);
        dailyDTOC.setData(new ArrayList<>());
        dailyDTOC.getData().add(dailyCurrently);
        forecastCelsius.setDaily(dailyDTOC);

        responseEntityC = new ResponseEntity<>(forecastCelsius, HttpStatus.ACCEPTED);
        responseEntityF = new ResponseEntity<>(forecastFahrenheit, HttpStatus.ACCEPTED);
    }

    @Test
    public void getWeatherNowAllValidC() {
        WeatherRequest request = new WeatherRequest("London", "C");
        request.setTime(now);
        when(darkSkyResource.getWeatherFromDarksky(any(), any())).thenReturn(responseEntityC);

        ResponseEntity<WeatherConditions> response = weatherService.getWeatherNow(request);

        assertEquals("15.0°C", response.getBody().getTemperature());
        assertEquals("25%", response.getBody().getHumidity());
        assertEquals("cloudy-day", response.getBody().getWeatherType());
        assertNull(response.getBody().getError());
    }

    @Test
    public void getWeatherNowAllValidF() {
        WeatherRequest request = new WeatherRequest("London", "F");
        request.setTime(now);
        when(darkSkyResource.getWeatherFromDarksky(any(), any())).thenReturn(responseEntityF);

        ResponseEntity<WeatherConditions> response = weatherService.getWeatherNow(request);

        assertEquals(response.getStatusCode(), HttpStatus.OK);
        assertEquals("59.0°F", response.getBody().getTemperature());
        assertEquals("25%", response.getBody().getHumidity());
        assertEquals("cloudy-day", response.getBody().getWeatherType());
        assertNull(response.getBody().getError());
    }

    @Test
    public void getWeatherNowInvalidUnit() {
        WeatherRequest request = new WeatherRequest("London", "M");
        request.setTime(now);
        when(darkSkyResource.getWeatherFromDarksky(any(), any())).thenReturn(responseEntityC);

        ResponseEntity<WeatherConditions> response = weatherService.getWeatherNow(request);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNotNull(response.getBody().getError());
        assertEquals(Constants.WRONG_UNIT, response.getBody().getError());
        assertNull(response.getBody().getWeatherType());
        assertNull(response.getBody().getTemperature());
        assertNull(response.getBody().getHumidity());

    }

    @Test
    public void getPastWeek() {
        WeatherRequest request = new WeatherRequest("London", "F");
        request.setTime(now);
        when(darkSkyResource.getWeatherFromDarksky(any(), any())).thenReturn(responseEntityC);
        ArrayList<WeatherConditions> response = weatherService.getPastWeek(request);
        assertNotNull(response);
        assertFalse(response.isEmpty());
        assertEquals(7, response.size());
    }
}
