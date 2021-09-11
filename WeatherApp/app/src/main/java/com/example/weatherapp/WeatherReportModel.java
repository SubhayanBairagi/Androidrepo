package com.example.weatherapp;

public class WeatherReportModel {

    private int id;

    public String getApplicable_date() {
        return applicable_date;
    }

    public void setApplicable_date(String applicable_date) {
        this.applicable_date = applicable_date;
    }

    private String applicable_date;
    private String weather_state_name;
    private String getWeather_state_abbr;
    private String wind_direction_compass;
    private float min_temp;
    private float max_temp;
    private float wind_speed;
    private float wind_direction;
    private float air_pressure;
    private int humidity;

    public WeatherReportModel(int id, String weather_state_name, String getWeather_state_abbr, String wind_direction_compass, float min_temp, float max_temp, float wind_speed, float wind_direction, float air_pressure, int humidity) {
        this.id = id;
        this.weather_state_name = weather_state_name;
        this.getWeather_state_abbr = getWeather_state_abbr;
        this.wind_direction_compass = wind_direction_compass;
        this.min_temp = min_temp;
        this.max_temp = max_temp;
        this.wind_speed = wind_speed;
        this.wind_direction = wind_direction;
        this.air_pressure = air_pressure;
        this.humidity = humidity;
    }

    public WeatherReportModel() {
    }

    @Override
    public String toString() {
        return
                "1. Date --> " + applicable_date+ "\n2. Weather State --> "+weather_state_name+
                "\n3. Low temp. --> " + min_temp +" °C" +
                "\n4. High temp. --> " + max_temp +" °C" +
                "\n5. Wind speed --> " + wind_speed + " feet per minute, or FPM"+
                "\n6. Wind direction --> " + wind_direction + " °" +
                "\n7. Air_pressure --> " + air_pressure + " pounds per square foot" +
                "\n8. Humidity --> " + humidity+" %"+"\n" ;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWeather_state_name() {
        return weather_state_name;
    }

    public void setWeather_state_name(String weather_state_name) {
        this.weather_state_name = weather_state_name;
    }

    public String getGetWeather_state_abbr() {
        return getWeather_state_abbr;
    }

    public void setGetWeather_state_abbr(String getWeather_state_abbr) {
        this.getWeather_state_abbr = getWeather_state_abbr;
    }

    public String getWind_direction_compass() {
        return wind_direction_compass;
    }

    public void setWind_direction_compass(String wind_direction_compass) {
        this.wind_direction_compass = wind_direction_compass;
    }

    public float getMin_temp() {
        return min_temp;
    }

    public void setMin_temp(float min_temp) {
        this.min_temp = min_temp;
    }

    public float getMax_temp() {
        return max_temp;
    }

    public void setMax_temp(float max_temp) {
        this.max_temp = max_temp;
    }

    public float getWind_speed() {
        return wind_speed;
    }

    public void setWind_speed(float wind_speed) {
        this.wind_speed = wind_speed;
    }

    public float getWind_direction() {
        return this.wind_direction;
    }

    public void setWind_direction(float wind_direction) {
        this.wind_direction = wind_direction;
    }

    public float getAir_pressure() {
        return air_pressure;
    }

    public void setAir_pressure(float air_pressure) {
        this.air_pressure = air_pressure;
    }

    public int getHumidity() {
        return humidity;
    }

    public void setHumidity(int humidity) {
        this.humidity = humidity;
    }
}
