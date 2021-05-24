package com.assignment.spring.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class WindEntity {
    @Column(name = "wind_speed")
    private Double speed;
    @Column(name = "wind_deg")
    private Integer deg;
}
