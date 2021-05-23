package com.assignment.spring.service.impl;

import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.dto.WeatherDataHolderDto;
import com.assignment.spring.exception.CityNotFoundException;
import com.assignment.spring.exception.SendRequestException;
import com.assignment.spring.mapping.WeatherMapper;
import com.assignment.spring.persistence.entity.WeatherDataHolderEntity;
import com.assignment.spring.persistence.repository.WeatherDataHolderEntityRepository;
import com.assignment.spring.service.WeatherService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
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
        WeatherResponse body = sendRequest(city);
        log.info("[MappingToEntityForSaving] city: {}", city);
        WeatherDataHolderEntity weatherDataHolderEntity = weatherMapper.toWeatherDataHolderEntity(body);
        log.info("[SavingNewWeatherInfo] city: {}", city);
        WeatherDataHolderEntity savedWeatherDataHolderEntity = weatherRepository.save(weatherDataHolderEntity);
        log.info("[MappingToDtoForReturn] city: {}", city);
        return weatherMapper.toWeatherDataHolderDto(savedWeatherDataHolderEntity);
    }


    private WeatherResponse sendRequest(String city) {
        UriComponentsBuilder uriComponentsBuilder = getUriComponentsBuilder(city);
        HttpHeaders httpHeaders = getHttpHeaders();

        ResponseEntity<WeatherResponse> result;
        try {
            log.info("[SendingRequestToThirdPartiApi] city: {}", city);
            result = restTemplate.exchange(
                    uriComponentsBuilder.toUriString(),
                    HttpMethod.GET,
                    new HttpEntity<>(httpHeaders),
                    WeatherResponse.class);
        } catch (HttpClientErrorException r) {
            HttpStatus status = r.getStatusCode();
            if (status == HttpStatus.NOT_FOUND) {
                log.error("[Can'tFindCityByName] in third parti API, city: {}", city);
                throw new CityNotFoundException(String.format("Can't find city by name: %s", city));
            }
            log.error("[UnexpectedResponseStatus] status: {}", HttpStatus.INTERNAL_SERVER_ERROR);
            throw new SendRequestException(String.format("Unexpected response status: %s", HttpStatus.INTERNAL_SERVER_ERROR));

        }
        return result.getBody();
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
