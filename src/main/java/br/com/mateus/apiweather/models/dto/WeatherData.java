package br.com.mateus.apiweather.models.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class WeatherData {
    
    private double temp;
    private int humidity ;
    private double wind;
    private String description;

    @Override
    public String toString() {
        return "Temperatura: " +
            + temp 
            + " ºC"
            + "\nUmidade: "
            + humidity
            + "%"
            + "Description: "
            + description;
    }
}
