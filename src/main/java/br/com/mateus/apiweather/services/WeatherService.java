package br.com.mateus.apiweather.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {

    private static final String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat=";
    private RestTemplate restTemplate;
    
    private String apikey = "b3e8ca870c7f18204d67672faacce21d";

    public boolean validateLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    public boolean validateLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    public WeatherData getWeatherDataByCoordinates(double lat, double lon) {
        String apiUrl = OPEN_WEATHER_API_URL + lat + "&lon=" + lon +
         "&units=metric&lang=pt_br&exclude=minutely, hourly, daily, alerts&appid=" + apikey; 
        ResponseEntity<WeatherData> response = restTemplate.exchange(apiUrl, HttpMethod.GET, null, WeatherData.class);
        return response.getBody();
    }
} 
