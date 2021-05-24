package com.assignment.spring.dto;

import lombok.Data;

@Data
public class MainDto {

    private Double temp;

    private Integer pressure;

    private Integer humidity;

    private Double tempMin;

    private Double tempMax;
}
