package com.assignment.spring.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class MainEntity {
    @Column(name = "main_temp")
    private Double temp;

    @Column(name = "main_pressure")
    private Integer pressure;

    @Column(name = "main_humidity")
    private Integer humidity;

    @Column(name = "main_temp_min")
    private Double tempMin;

    @Column(name = "main_temp_max")
    private Double tempMax;
}
