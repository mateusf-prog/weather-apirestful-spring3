package br.com.mateus.apiweather.models;

import java.util.List;

import org.springframework.stereotype.Component;

import lombok.Getter;

@Getter
@Component
public class CurrentWheater {

    private long dt;
    private long sunrise;
    private long sunset;
    private double temp;
    private double feelsLike;
    private int pressure;
    private int humidity;
    private double dewPoint;
    private double uvi;
    private int clouds;
    private int visibility;
    private double windSpeed;
    private int windDeg;
    private double windGust;
    private List<WeatherInfo> weather;
}
