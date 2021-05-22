package com.assignment.spring.service.impl;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.persistence.entity.WeatherEntity;
import com.assignment.spring.persistence.reposotory.WeatherRepository;
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

    @Value("${api.weather.get}")
    private String apiWeatherGetUrl;
    @Value("${application.id}")
    private String appId;

    private final RestTemplate restTemplate;
    private final WeatherRepository weatherRepository;

    @Override
    public WeatherEntity getWeatherForCity(String city) {

        //TODO MAPPING OTHERS FIELDS
        HttpEntity<WeatherResponse> response = restTemplate.exchange(
                getUriComponentsBuilder(city).toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                WeatherResponse.class);

        return mapper(response.getBody());
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

    //TODO mapper to separate interface
    private WeatherEntity mapper(WeatherResponse response) {
        WeatherEntity entity = new WeatherEntity();
        entity.setCity(response.getName());
        entity.setCountry(response.getSys().getCountry());
        entity.setTemperature(response.getMain().getTemp());

        return weatherRepository.save(entity);
    }
}
