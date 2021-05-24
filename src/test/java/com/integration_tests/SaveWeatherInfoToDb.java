package com.integration_tests;

import com.assignment.spring.Application;
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
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SaveWeatherInfoToDb {

    @Autowired
    private WeatherDataHolderEntityRepository repository;

    @Test
    public void saveWeatherHolderEntityToDb_ifOK_entityWillSaved() {
        WeatherDataHolderEntity entity = initWeatherDataHolderEntity();
        String name = entity.getName();
        repository.save(entity);
        WeatherDataHolderEntity foundByName = repository.findByName(name);
        long count = repository.count();
        assertThat(count, is(1L));
        assertNotNull(foundByName);
    }

    private WeatherDataHolderEntity initWeatherDataHolderEntity() {
        WeatherDataHolderEntity entity = new WeatherDataHolderEntity();

        entity.setDt(7);
        entity.setCityId(123);
        entity.setVisibility(7979);
        entity.setCod(9090);
        entity.setBase("base");
        entity.setName("lucky_name_777");

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

}
