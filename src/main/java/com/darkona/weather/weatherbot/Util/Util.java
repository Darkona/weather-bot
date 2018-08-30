package com.darkona.weather.weatherbot.Util;

import java.text.DecimalFormat;

public class Util {

    private static DecimalFormat df2 = new DecimalFormat(".##");

    public static double FahrenheitToKelvin(double degrees){
        return  5/9 * (degrees - 32) + 273;
    }

    public static double KelvinToCelsius(double degrees){
        return degrees - 273;
    }

    public static double FahrenheitToCelsius(double degrees){
        return KelvinToCelsius(FahrenheitToKelvin(degrees));
    }

    public String formatTwoDecimals(double number){
        return df2.format(number);
    }

}
