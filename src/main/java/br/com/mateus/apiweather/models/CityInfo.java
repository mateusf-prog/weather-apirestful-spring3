package br.com.mateus.apiweather.models;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CityInfo {

    private int statuscode;
    private CityCopyright copiright;
    private List<CityMessages> messages;

}
