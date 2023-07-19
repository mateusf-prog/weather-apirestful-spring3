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
        return city + "/" + state + " - " + country;
    }
}
