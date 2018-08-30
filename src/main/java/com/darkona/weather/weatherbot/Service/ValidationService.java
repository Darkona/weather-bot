package com.darkona.weather.weatherbot.Service;

import com.darkona.weather.weatherbot.Util.Constants;
import org.springframework.stereotype.Service;

import java.util.regex.Pattern;

@Service
public class ValidationService {

    public boolean isValidUnit(String unit){
        final Pattern pattern = Pattern.compile("^(["+ Constants.CELSIUS + Constants.FAHRENHEIT +"])");
        return pattern.matcher(unit).matches();
    }

}
