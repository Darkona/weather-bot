package com.darkona.weather.weatherbot.controller;

import com.darkona.weather.weatherbot.request.WeatherRequest;
import com.darkona.weather.weatherbot.service.WeatherService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;

@RunWith(MockitoJUnitRunner.class)
public class WeatherControllerTest {

    @InjectMocks
    WeatherController weatherController;

    @Mock
    WeatherService weatherService;
    private MockMvc mockMvc;
    private ObjectMapper objectMapper;

    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(weatherController).build();
        objectMapper = new ObjectMapper();
//        objectMapper.registerModule(new JavaTimeModule());
    }

    @Test
    public void currentWeather() throws Exception {

        String city = "SF";
        String unit = "C";
        WeatherRequest weatherRequest = new WeatherRequest(city, unit);

        MockHttpServletResponse response = mockMvc.perform(
                get("/weather/currentWeather")
                        .param("city", city)
                        .param("unit", unit)
        ).andReturn().getResponse();

        assertThat(response.getStatus(), is(HttpStatus.OK.value()));
        then(weatherService).should(times(1))
                .getWeatherNow(weatherRequest);
    }

    @Test
    public void pastWeek() {
    }
}