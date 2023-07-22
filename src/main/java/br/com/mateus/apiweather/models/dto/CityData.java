package br.com.mateus.apiweather.models.dto;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Component
public class CityData {
    
    private String country;
    private String city;
    private String state;

    @Override
    public String toString() {
        if (city.isEmpty() && country.isEmpty() && state.isEmpty()) {
            return "No city, state and country information.";
        } else {
            return city + "/" + state + " - " + country; 
        }
    }
}
