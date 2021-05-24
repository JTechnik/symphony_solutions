package com.assignment.spring.dto;

import lombok.Data;

import java.util.List;


@Data
public class WeatherDataHolderDto {

    private Integer id;

    private String base;

    private String name;

    private Integer dt;

    private Integer cityId;

    private Integer visibility;

    private Integer cod;

    private List<WeatherDto> weather;

    private CoordDto coord;

    private MainDto main;

    private WindDto wind;

    private CloudsDto clouds;

    private SysDto sys;
}
