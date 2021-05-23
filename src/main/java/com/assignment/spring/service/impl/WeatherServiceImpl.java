package com.assignment.spring.service.impl;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.dto.WeatherDataHolderDto;
import com.assignment.spring.mapping.WeatherMapper;
import com.assignment.spring.persistence.entity.WeatherDataHolderEntity;
import com.assignment.spring.persistence.reposotory.WeatherDataHolderEntityRepository;
import com.assignment.spring.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
@Slf4j
@RequiredArgsConstructor
public class WeatherServiceImpl implements WeatherService {

    @Value("${api.weather.url}")
    private String apiWeatherGetUrl;
    @Value("${application.id}")
    private String appId;

    private final RestTemplate restTemplate;
    private final WeatherDataHolderEntityRepository weatherRepository;
    private final WeatherMapper weatherMapper;

    @Override
    public WeatherDataHolderDto getWeatherForCity(String city) {

        HttpEntity<WeatherResponse> response = restTemplate.exchange(
                getUriComponentsBuilder(city).toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                WeatherResponse.class);
        WeatherResponse body = response.getBody();
        WeatherDataHolderEntity weatherDataHolderEntity = weatherMapper.toWeatherDataHolderEntity(body);
        WeatherDataHolderEntity savedWeatherDataHolderEntity = weatherRepository.save(weatherDataHolderEntity);
        return weatherMapper.toWeatherDataHolderDto(savedWeatherDataHolderEntity);
    }

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    private UriComponentsBuilder getUriComponentsBuilder(String city) {
        return UriComponentsBuilder.fromHttpUrl(apiWeatherGetUrl)
                .queryParam("APPID", appId)
                .queryParam("q", city);
    }
}
