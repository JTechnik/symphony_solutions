package com.assignment.spring.service;

import com.assignment.spring.dto.WeatherDataHolderDto;

public interface WeatherService {

    WeatherDataHolderDto getWeatherForCity(String city);
}
