package br.com.mateus.apiweather.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import br.com.mateus.apiweather.exception.ApiException;
import br.com.mateus.apiweather.exception.LatitudeLongitudeException;
import br.com.mateus.apiweather.models.city.CityResponse;
import br.com.mateus.apiweather.models.dto.CityData;
import br.com.mateus.apiweather.models.dto.WeatherData;
import br.com.mateus.apiweather.models.weather.WeatherResponse;

@Service
public class WeatherService {

    private static final String OPEN_WEATHER_API_URL = "https://api.openweathermap.org/data/3.0/onecall?lat=";
    private static final String MAP_QUEST_API_URL = "https://www.mapquestapi.com/geocoding/v1/reverse?key=";

    private String apiKeyOpenWeather = "b3e8ca870c7f18204d67672faacce21d";
    private String apiKeyMapQuest = "lRcKZSTRNKOtLlmx8gmL3W3FpGC5twxJ";

    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private WeatherData weatherData;
    @Autowired
    private CityData cityData;

    // dto
    public ResponseEntity<?> getWeatherDataByCoordinates(double lat, double lon) {
        CityData cd = getAdressByCoordinates(responseApiAdress(lat, lon));
        WeatherResponse wr = responseApiWeather(lat, lon);
        if (wr != null) {
            weatherData.setTemp(wr.getCurrent().getTemp());
            weatherData.setHumidity(wr.getCurrent().getHumidity());
            weatherData.setWind(wr.getCurrent().getWind_speed());
            weatherData.setDescription(wr.getCurrent().getWeather().get(0).getDescription());
            weatherData.setCityData(cd);
            return ResponseEntity.ok(weatherData);
        } else {
            throw new LatitudeLongitudeException("Error in entering latitude or longitude!");
        }
    }

    public WeatherResponse responseApiWeather(double lat, double lon) {
        String apiUrl = OPEN_WEATHER_API_URL + lat + "&lon=" + lon +
                "&units=metric&lang=pt_br&exclude=minutely,hourly,daily,alerts&appid=" + apiKeyOpenWeather;
        if (validateLatiduteLongitude(lat, lon)) {
            try {
                WeatherResponse weatherResponse = restTemplate.getForObject(apiUrl, WeatherResponse.class);
                return weatherResponse;
            } catch (Exception e) {
                throw new ApiException("Error returning data from weather API!");
            }
        } else {
            throw new LatitudeLongitudeException("Error in entering latitude or longitude!");
        }
    }

    // dto
    public CityData getAdressByCoordinates(CityResponse cityResponse) {
        if (cityResponse != null) {
            cityData.setCity(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea5());
            cityData.setState(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea3());
            cityData.setCountry(cityResponse.getResults().get(0).getLocations().get(0).getAdminArea1());
            return cityData;
        } else {
            throw new LatitudeLongitudeException("Error in entering latitude or longitude!");
        }
    }

    public CityResponse responseApiAdress(double lat, double lon) {
        String apiUrl = MAP_QUEST_API_URL + apiKeyMapQuest + "&location=" + lat + "," + lon;
        if (validateLatiduteLongitude(lat, lon)) {
            try {
                CityResponse cityResponse = restTemplate.getForObject(apiUrl, CityResponse.class);
                return cityResponse;
            } catch (RuntimeException e) {
                throw new ApiException("Error return API adress!");
            }
        } else {
            throw new LatitudeLongitudeException("Error in entering latitude or longitude!");
        }
    }
    
    public boolean validateLatiduteLongitude(double latitude, double longitude) {
        if ((latitude >= -90 && latitude <= 90) && (longitude >= -180 && longitude <= 180)) {
            return true;
        } else {
            return false;
        }
    }
}
