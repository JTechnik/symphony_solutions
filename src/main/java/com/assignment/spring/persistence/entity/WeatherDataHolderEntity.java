package com.assignment.spring.persistence.entity;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "weather_data_holder")
@Data
public class WeatherDataHolderEntity {

    @Id
    @SequenceGenerator(name = "weather_data_holder_seg_generator", sequenceName = "weather_data_holder_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather_data_holder_seg_generator")
    private Integer id;

    @Column(name = "base")
    private String base;

    @Column(name = "name")
    private String name;

    @Column(name = "dt")
    private Integer dt;

    @Column(name = "external_id")
    private Integer externalId;

    @Column(name = "visibility")
    private Integer visibility;

    @Column(name = "cod")
    private Integer cod;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "weatherDataHolder")
    private List<WeatherEntity> weather = new ArrayList<>();

    @Embedded
    private CoordEntity coord;

    @Embedded
    private MainEntity main;

    @Embedded
    private WindEntity wind;

    @Embedded
    private CloudsEntity clouds;

    @Embedded
    private SysEntity sys;

    public void setWeather(List<WeatherEntity> weather) {
        weather.forEach(weatherEntity -> weatherEntity.setWeatherDataHolder(this));
        this.weather = weather;
    }
}
