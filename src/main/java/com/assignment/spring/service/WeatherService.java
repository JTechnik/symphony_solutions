package com.assignment.spring.service;

import com.assignment.spring.persistence.entity.WeatherEntity;

public interface WeatherService {
    //TODO WeatherEntity -> WeatherDTO
    WeatherEntity getWeatherForCity(String city);

}
