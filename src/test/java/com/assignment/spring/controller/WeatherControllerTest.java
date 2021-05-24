package com.assignment.spring.controller;

import com.assignment.spring.dto.CloudsDto;
import com.assignment.spring.dto.CoordDto;
import com.assignment.spring.dto.MainDto;
import com.assignment.spring.dto.SysDto;
import com.assignment.spring.dto.WeatherDataHolderDto;
import com.assignment.spring.dto.WeatherDto;
import com.assignment.spring.dto.WindDto;
import com.assignment.spring.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(WeatherController.class)
public class WeatherControllerTest {

    private static final String CITY_NAME = "lucky_name_777888";

    @MockBean
    private WeatherService weatherService;

    @Autowired
    private MockMvc mockMvc;

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

    @Test
    public void getWeatherInfoByCityName_ifOk_weatherInfoReturned() throws Exception {
        this.mockMvc.perform(get("/weather?city=" + CITY_NAME)).andExpect(status().isOk());
        verify(weatherService, times(1)).getWeatherForCity(CITY_NAME);
        when(weatherService.getWeatherForCity(CITY_NAME)).thenReturn(initWeatherDataHolderDto());
    }
}
