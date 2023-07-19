package br.com.mateus.apiweather.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResponse {
    
    private List<CityLocations> locations;

}
