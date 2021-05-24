package com.integration_tests;

import com.assignment.spring.Application;
import com.assignment.spring.api.WeatherResponse;
import com.assignment.spring.service.WeatherService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SendRequestToThirdPartyApi {

    @Value("${test.city.name}")
    private String cityName;

    @Autowired
    private WeatherService weatherService;

    @Test
    public void sendRequestGetWeatherToRemoteUrl_ifOk_weatherInfoReturned(){
        WeatherResponse weatherResponse = weatherService.sendRequest(cityName);
        assertNotNull(weatherResponse);
        String name = weatherResponse.getName();
        assertThat(name, is(cityName));
    }
}
