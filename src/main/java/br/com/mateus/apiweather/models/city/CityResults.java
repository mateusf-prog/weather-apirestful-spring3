package br.com.mateus.apiweather.models.city;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityResults {

    private CityProvidedLocation providedLocation;
    private List<CityLocations> locations;

}
