package br.com.mateus.apiweather.models.weather;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherResponse {

    private double lat;
    private double lon;
    private String timezone;
    private int timezoneO_offset;
    private CurrentWeather current;
}
