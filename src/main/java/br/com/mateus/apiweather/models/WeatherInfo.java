package br.com.mateus.apiweather.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WeatherInfo {
    private int id;
    private String main;
    private String description;
    private String icon;
}
