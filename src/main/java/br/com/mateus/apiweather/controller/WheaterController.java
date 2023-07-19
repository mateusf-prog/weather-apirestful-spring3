package br.com.mateus.apiweather.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
public class WheaterController {

    @GetMapping
    public String welcome() {
        return "Welcome to the wheater API - by Mateus";
    }

}
