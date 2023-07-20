package br.com.mateus.apiweather.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

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

    public ResponseEntity<?> getWeatherDataByCoordinates(double lat, double lon) {
        if (validateLatiduteLongitude(lat, lon)) {
            String apiUrl = OPEN_WEATHER_API_URL + lat + "&lon=" + lon +
            "&units=metric&lang=pt_br&exclude=minutely,hourly,daily,alerts&appid=" + apiKeyOpenWeather; 
            WeatherResponse weatherResponse = restTemplate.getForObject(apiUrl,WeatherResponse.class);
            if (weatherResponse != null) {
                weatherData.setTemp(weatherResponse.getCurrent().getTemp());
                weatherData.setHumidity(weatherResponse.getCurrent().getHumidity());
                weatherData.setWind(weatherResponse.getCurrent().getWind_speed());
                weatherData.setDescription(weatherResponse.getCurrent().getWeather().get(0).getDescription());
                weatherData.setCityData(getCityByCoordinates(lat, lon));
                return ResponseEntity.ok(weatherData.toString());
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } else {
            return ResponseEntity.ok("Incorrect Latitude or Longitude");
        }
    }

    public CityData getCityByCoordinates(double lat, double lon) {
        String apiUrl = MAP_QUEST_API_URL + apiKeyMapQuest + "&location=" + lat + "," + lon;
        CityResponse cityResponse = restTemplate.getForObject(apiUrl,CityResponse.class);
        if (cityResponse != null) {
            cityData.setCity(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea5());
            cityData.setState(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea3());
            cityData.setCountry(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea1());
            return cityData;
        } else {
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

