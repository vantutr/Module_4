package com.codegym.baitap1.controller;

import com.codegym.baitap1.model.Weather;
import com.codegym.baitap1.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/{location}")
    public ResponseEntity<Weather> getWeather(@PathVariable String location) {
        Weather weather = weatherService.getWeatherByLocation(location);
        return ResponseEntity.ok(weather);
    }
}
