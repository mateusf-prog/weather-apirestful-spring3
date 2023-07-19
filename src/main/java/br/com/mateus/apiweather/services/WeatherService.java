package br.com.mateus.apiweather.services;

import java.io.IOException;

import javax.naming.spi.DirStateFactory.Result;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.mateus.apiweather.models.WeatherResponse;
import br.com.mateus.apiweather.models.dto.WeatherData;

@Service
public class WeatherService {

    private static final String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat=";
    private String apiKeyOpenWeather = "b3e8ca870c7f18204d67672faacce21d";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherData weatherData;

    public boolean validateLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    public boolean validateLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    public ResponseEntity<?> getWeatherDataByCoordinates(double lat, double lon) {
        if (validateLatitude(lat) && validateLongitude(lon)) {
            String apiUrl = OPEN_WEATHER_API_URL + lat + "&lon=" + lon +
            "&units=metric&lang=pt_br&exclude=minutely,hourly,daily,alerts&appid=" + apiKeyOpenWeather; 
            WeatherResponse weatherResponse = restTemplate.getForObject(apiUrl,WeatherResponse.class);
            if (weatherResponse != null) {
                weatherData.setTemp(weatherResponse.getCurrent().getTemp());
                weatherData.setHumidity(weatherResponse.getCurrent().getHumidity());
                weatherData.setWind(weatherResponse.getCurrent().getWind_speed());
                weatherData.setDescription(weatherResponse.getCurrent().getWeather().get(0).getDescription());
                weatherData.setCity(weatherResponse.getTimezone());
                return ResponseEntity.ok(weatherData.toString());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.ok("Incorrect Latitude or Longitude");
        }
    }
}

