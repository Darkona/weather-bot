package com.darkona.weather.weatherbot.controller;

import com.darkona.weather.weatherbot.response.ResourceHelp;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/", produces = "application/hal+json")
public class HomeController {

    @GetMapping("/")
    @ResponseBody
    public ResourceHelp home() throws Exception {
        return new ResourceHelp();
    }
}
