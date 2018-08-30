package com.darkona.weather.weatherbot.util;

import java.text.DecimalFormat;

public class Util {

    public static String formatTwoDecimals(double number){
        DecimalFormat df2 = new DecimalFormat(".##");
        return df2.format(number);
    }

    public static String formatNoDecimals(double number) {
        DecimalFormat df2 = new DecimalFormat("");
        return df2.format(number);
    }
}
