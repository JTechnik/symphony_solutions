package com.assignment.spring.controller;

import com.assignment.spring.persistence.entity.WeatherEntity;
import com.assignment.spring.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(value = "/weather")
@RequiredArgsConstructor
public class WeatherController {

    private final WeatherService weatherService;

    //TODO WeatherEntity -> WeatherDTO
    //TODO other endpoints
    @GetMapping
    public WeatherEntity weather(@RequestParam String city) {
        log.info("[RequestForGetWeatherInfo] city: {}", city);
        return weatherService.getWeatherForCity(city);
    }
}
