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
    private CityData cityData;

    @Override
    public String toString() {
        return "Temperatura: " 
            + String.format("%.1f", temp)
            + " ºC"
            + "\nUmidade: "
            + humidity
            + "%"
            + "\nVento: "
            + wind
            + "km/h\n"
            + description.toUpperCase()
            + "\n"
            + cityData;
    }
}
    
