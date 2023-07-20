package br.com.mateus.apiweather.models;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityOptions {

    private String maxResults;
    private boolean ignoreLatLngInput;

}
