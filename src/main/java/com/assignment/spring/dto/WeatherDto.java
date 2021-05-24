package com.assignment.spring.dto;

import lombok.Data;


@Data
public class WeatherDto {

    private Integer id;

    private Integer externalId;

    private String main;

    private String description;

    private String icon;
}
