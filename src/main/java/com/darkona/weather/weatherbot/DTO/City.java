package com.darkona.weather.weatherbot.DTO;

public class City {

    private String name;
    private String countryName;
    private String latitude;
    private String longitude;

    public static City getLondon(){
        City london = new City();
        london.setCountryName("England");
        london.setName("London");
        london.setLatitude("51.50853");
        london.setLongitude("-0.12574");
        return london;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}
