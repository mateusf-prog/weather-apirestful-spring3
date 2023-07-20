package br.com.mateus.apiweather.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.mateus.apiweather.services.WeatherService;

@RestController
@RequestMapping("/")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @GetMapping
    public String home() {
        return "Home page!";
    }   

    @GetMapping("/{lat},{lon}")
    public ResponseEntity<?> getWeatherData (@PathVariable double lat, @PathVariable double lon) {
        return weatherService.getWeatherDataByCoordinates(lat, lon);
    }
}
