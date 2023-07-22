package br.com.mateus.apiweather.services;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.introspect.TypeResolutionContext.Empty;

import br.com.mateus.apiweather.models.city.CityResponse;
import br.com.mateus.apiweather.models.dto.CityData;
import br.com.mateus.apiweather.models.dto.WeatherData;
import br.com.mateus.apiweather.models.weather.WeatherResponse;

@Service
public class WeatherService {
    
    private static final String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat=";
    private static final String MAP_QUEST_API_URL = "https://www.mapquestapi.com/geocoding/v1/reverse?key=";

    private String apiKeyOpenWeather = "b3e8ca870c7f18204d67672faacce21d";
    private  String apiKeyMapQuest = "lRcKZSTRNKOtLlmx8gmL3W3FpGC5twxJ";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherData weatherData;
    @Autowired
    private CityData cityData;

    public ResponseEntity<?> getWeatherDataByCoordinates(ResponseEntity<?> objEntity) {
        if (C) {
            weatherData.setTemp(weatherResponse.getCurrent().getTemp());
            weatherData.setHumidity(weatherResponse.getCurrent().getHumidity());
            weatherData.setWind(weatherResponse.getCurrent().getWind_speed());
            weatherData.setDescription(weatherResponse.getCurrent().getWeather().get(0).getDescription());
            weatherData.setCityData(getAdressByCoordinates(lat, lon));
        }

    public WeatherResponse responseApiWeather() {
        String apiUrl = OPEN_WEATHER_API_URL + lat + "&lon=" + lon +
        "&units=metric&lang=pt_br&exclude=minutely,hourly,daily,alerts&appid=" + apiKeyOpenWeather;
        try {
            WeatherResponse weatherResponse = restTemplate.getForObject(apiUrl,WeatherResponse.class);
            return weatherResponse;
        } catch (Exception e){
            return null;
        }
    }
    
    public CityData getAdressByCoordinates(CityResponse cityResponse) {
        if (cityResponse != null) {
            cityData.setCity(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea5());
            cityData.setState(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea3());
            cityData.setCountry(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea1());
            return cityData;
        } else {
            return null;
        }
    }

    public CityResponse responseApiAdress(double lat, double lon) {
        String apiUrl = MAP_QUEST_API_URL + apiKeyMapQuest + "&location=" + lat + "," + lon;
        try {
            CityResponse cityResponse = restTemplate.getForObject(apiUrl,CityResponse.class);
            return cityResponse;
        } catch (Exception e) {
            return null;
        }
    }

    public boolean validateLatiduteLongitude(double latitude, double longitude) {
        if ((latitude >= -90 && latitude <= 90) && (longitude >= -180 && longitude <= 180)) {
            return true;
        } else{
            return false;
        }
    }
}

