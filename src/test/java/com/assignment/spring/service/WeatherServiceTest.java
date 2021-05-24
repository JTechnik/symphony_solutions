package com.assignment.spring.service;

import com.assignment.spring.api.Clouds;
import com.assignment.spring.api.Coord;
import com.assignment.spring.api.Main;
import com.assignment.spring.api.Sys;
import com.assignment.spring.api.Weather;
import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.api.Wind;
import com.assignment.spring.dto.CloudsDto;
import com.assignment.spring.dto.CoordDto;
import com.assignment.spring.dto.MainDto;
import com.assignment.spring.dto.SysDto;
import com.assignment.spring.dto.WeatherDataHolderDto;
import com.assignment.spring.dto.WeatherDto;
import com.assignment.spring.dto.WindDto;
import com.assignment.spring.exception.CityNotFoundException;
import com.assignment.spring.exception.SendRequestException;
import com.assignment.spring.persistence.entity.CloudsEntity;
import com.assignment.spring.persistence.entity.CoordEntity;
import com.assignment.spring.persistence.entity.MainEntity;
import com.assignment.spring.persistence.entity.SysEntity;
import com.assignment.spring.persistence.entity.WeatherDataHolderEntity;
import com.assignment.spring.persistence.entity.WeatherEntity;
import com.assignment.spring.persistence.entity.WindEntity;
import com.assignment.spring.persistence.repository.WeatherDataHolderEntityRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WeatherServiceTest {

    private static final String CITY_NAME = "lucky_name_777888_luke";

    @Value("${api.weather.url}")
    private String apiWeatherGetUrl;
    @Value("${application.id}")
    private String appId;

    @MockBean
    private WeatherDataHolderEntityRepository repository;
    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private WeatherService weatherService;

    private HttpHeaders getHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.ACCEPT, MediaType.APPLICATION_JSON_VALUE);
        return headers;
    }

    @Test
    public void getWeatherInfoByCityName_ifOk_notFoundExceptionThrown() {
        doThrow(CityNotFoundException.class).when(restTemplate).exchange(
                UriComponentsBuilder.fromHttpUrl(apiWeatherGetUrl)
                        .queryParam("APPID", appId)
                        .queryParam("q", CITY_NAME).toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                WeatherResponse.class);

        assertThrows(CityNotFoundException.class, () -> weatherService.getWeatherForCity(CITY_NAME));
    }

    @Test
    public void getWeatherInfoByCityName_ifOk_unexpectedStatusExceptionThrown() {
        doThrow(SendRequestException.class).when(restTemplate).exchange(
                UriComponentsBuilder.fromHttpUrl(apiWeatherGetUrl)
                        .queryParam("APPID", appId)
                        .queryParam("q", CITY_NAME).toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                WeatherResponse.class);

        assertThrows(SendRequestException.class, () -> weatherService.getWeatherForCity(CITY_NAME));
    }

    @Test
    public void getWeatherInfoByCityName_ifOk_weatherInfoReturned() {
        WeatherResponse weatherResponse = initWeatherResponse();
        WeatherDataHolderEntity entity = initWeatherDataHolderEntity();

        doReturn(ResponseEntity.ok(weatherResponse)).when(restTemplate).exchange(
                UriComponentsBuilder.fromHttpUrl(apiWeatherGetUrl)
                        .queryParam("APPID", appId)
                        .queryParam("q", CITY_NAME).toUriString(),
                HttpMethod.GET,
                new HttpEntity<>(getHttpHeaders()),
                WeatherResponse.class);

        doReturn(entity).when(repository).save(any());

        WeatherDataHolderDto weatherForCity = weatherService.getWeatherForCity(CITY_NAME);

        assertThat(weatherForCity.getName(), is(CITY_NAME));
        verify(repository, times(1)).save(any());
    }

    private WeatherResponse initWeatherResponse() {
        WeatherResponse weatherResponse = new WeatherResponse();

        weatherResponse.setDt(7);
        weatherResponse.setCityId(123);
        weatherResponse.setVisibility(7979);
        weatherResponse.setCod(9090);
        weatherResponse.setBase("base");
        weatherResponse.setName(CITY_NAME);

        Weather weatherDto = new Weather();
        weatherDto.setMain("main");
        weatherDto.setExternalId(7899);
        weatherDto.setIcon("9a");
        weatherDto.setDescription("descr");

        weatherResponse.setWeather(Arrays.asList(weatherDto));

        Clouds clouds = new Clouds();
        clouds.setAll(1);

        weatherResponse.setClouds(clouds);

        Coord coord = new Coord();
        coord.setLat(1.2);
        coord.setLon(1.3);

        weatherResponse.setCoord(coord);

        Main main = new Main();
        main.setTemp(1.3);
        main.setPressure(6);
        main.setHumidity(5);
        main.setTempMin(0.1);
        main.setTempMax(1.7);

        weatherResponse.setMain(main);

        Sys sys = new Sys();
        sys.setType(3);
        sys.setExternalId(2);
        sys.setMessage(12.2);
        sys.setCountry("country");
        sys.setSunrise(2);
        sys.setSunset(1);

        weatherResponse.setSys(sys);

        Wind wind = new Wind();
        wind.setDeg(1);
        wind.setSpeed(1.7);

        weatherResponse.setWind(wind);

        return weatherResponse;
    }

    private WeatherDataHolderEntity initWeatherDataHolderEntity() {
        WeatherDataHolderEntity entity = new WeatherDataHolderEntity();

        entity.setDt(7);
        entity.setCityId(123);
        entity.setVisibility(7979);
        entity.setCod(9090);
        entity.setBase("base");
        entity.setName(CITY_NAME);

        WeatherEntity weatherEntity = new WeatherEntity();
        weatherEntity.setMain("main");
        weatherEntity.setExternalId(7899);
        weatherEntity.setIcon("9a");
        weatherEntity.setDescription("descr");

        entity.setWeather(Arrays.asList(weatherEntity));

        CloudsEntity cloudsEntity = new CloudsEntity();
        cloudsEntity.setAll(1);

        entity.setClouds(cloudsEntity);

        CoordEntity coordEntity = new CoordEntity();
        coordEntity.setLat(1.2);
        coordEntity.setLon(1.3);

        entity.setCoord(coordEntity);

        MainEntity mainEntity = new MainEntity();
        mainEntity.setTemp(1.3);
        mainEntity.setPressure(6);
        mainEntity.setHumidity(5);
        mainEntity.setTempMin(0.1);
        mainEntity.setTempMax(1.7);

        entity.setMain(mainEntity);

        SysEntity sysEntity = new SysEntity();
        sysEntity.setType(3);
        sysEntity.setExternalId(2);
        sysEntity.setMessage(12.2);
        sysEntity.setCountry("country");
        sysEntity.setSunrise(2);
        sysEntity.setSunset(1);
        entity.setSys(sysEntity);

        WindEntity windEntity = new WindEntity();
        windEntity.setDeg(1);
        windEntity.setSpeed(1.7);

        entity.setWind(windEntity);

        return entity;
    }

    private WeatherDataHolderDto initWeatherDataHolderDto() {
        WeatherDataHolderDto weatherDataHolderDto = new WeatherDataHolderDto();

        weatherDataHolderDto.setDt(7);
        weatherDataHolderDto.setCityId(123);
        weatherDataHolderDto.setVisibility(7979);
        weatherDataHolderDto.setCod(9090);
        weatherDataHolderDto.setBase("base");
        weatherDataHolderDto.setName(CITY_NAME);

        WeatherDto weatherDto = new WeatherDto();
        weatherDto.setMain("main");
        weatherDto.setExternalId(7899);
        weatherDto.setIcon("9a");
        weatherDto.setDescription("descr");

        weatherDataHolderDto.setWeather(Arrays.asList(weatherDto));

        CloudsDto cloudsDto = new CloudsDto();
        cloudsDto.setAll(1);

        weatherDataHolderDto.setClouds(cloudsDto);

        CoordDto coordDto = new CoordDto();
        coordDto.setLat(1.2);
        coordDto.setLon(1.3);

        weatherDataHolderDto.setCoord(coordDto);

        MainDto mainDto = new MainDto();
        mainDto.setTemp(1.3);
        mainDto.setPressure(6);
        mainDto.setHumidity(5);
        mainDto.setTempMin(0.1);
        mainDto.setTempMax(1.7);

        weatherDataHolderDto.setMain(mainDto);

        SysDto sysDto = new SysDto();
        sysDto.setType(3);
        sysDto.setExternalId(2);
        sysDto.setMessage(12.2);
        sysDto.setCountry("country");
        sysDto.setSunrise(2);
        sysDto.setSunset(1);

        weatherDataHolderDto.setSys(sysDto);

        WindDto windDto = new WindDto();
        windDto.setDeg(1);
        windDto.setSpeed(1.7);

        weatherDataHolderDto.setWind(windDto);

        return weatherDataHolderDto;
    }
}
