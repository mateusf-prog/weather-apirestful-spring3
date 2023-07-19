package br.com.mateus.apiweather.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.atlis.location.nominatim.NominatimAPI;

import br.com.mateus.apiweather.models.WeatherResponse;
import br.com.mateus.apiweather.models.dto.WeatherData;

@Service
public class WeatherService {

    private static final String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat=";

    @Autowired
    private RestTemplate restTemplate;

    @Autowired
    private WeatherData weatherData;
    
    private String apikey = "b3e8ca870c7f18204d67672faacce21d";

    public boolean validateLatitude(double latitude) {
        return latitude >= -90 && latitude <= 90;
    }

    public boolean validateLongitude(double longitude) {
        return longitude >= -180 && longitude <= 180;
    }

    public ResponseEntity<?> getWeatherDataByCoordinates(double lat, double lon) {
        if (validateLatitude(lat) && validateLongitude(lon)) {
            String apiUrl = OPEN_WEATHER_API_URL + lat + "&lon=" + lon +
            "&units=metric&lang=pt_br&exclude=minutely,hourly,daily,alerts&appid=" + apikey; 
            WeatherResponse weatherResponse = restTemplate.getForObject(apiUrl,WeatherResponse.class);
            if (weatherResponse != null) {
                weatherData.setTemp(weatherResponse.getCurrent().getTemp());
                weatherData.setHumidity(weatherResponse.getCurrent().getHumidity());
                weatherData.setWind(weatherResponse.getCurrent().getWind_speed());
                weatherData.setDescription(weatherResponse.getCurrent().getWeather().get(0).getDescription());
                return ResponseEntity.ok(weatherData);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.ok("Incorrect Latitude or Longitude");
        }
    }

    public String getCityNameByCoordinates (double lat, double lon) {
        NominatimAPI config = new NominatimAPI();
        NominatimAPI nominatimAPI = new NominatimAPI();
    }
}

