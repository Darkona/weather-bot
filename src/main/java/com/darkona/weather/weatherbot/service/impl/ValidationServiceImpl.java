package com.darkona.weather.weatherbot.service.impl;

import com.darkona.weather.weatherbot.service.ValidationService;
import com.darkona.weather.weatherbot.util.Constants;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public boolean isValidUnit(String unit){
        final Pattern pattern = Pattern.compile("^(["+ Constants.CELSIUS + Constants.FAHRENHEIT +"])");
        return pattern.matcher(unit.toUpperCase()).matches();
    }


}
