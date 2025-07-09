package com.codegym.baitap1.service;

import com.codegym.baitap1.exception.LocationNotFoundException;
import com.codegym.baitap1.model.Weather;
import com.codegym.baitap1.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WeatherService {

    private final WeatherRepository weatherRepository;

    @Autowired
    public WeatherService(WeatherRepository weatherRepository) {
        this.weatherRepository = weatherRepository;
    }

    public Weather getWeatherByLocation(String location) {
        return weatherRepository.findByLocationIgnoreCase(location)
                .orElseThrow(() -> new LocationNotFoundException("Không tìm thấy thông tin thời tiết cho địa phương: " + location));
    }
}

