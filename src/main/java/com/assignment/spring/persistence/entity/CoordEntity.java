package com.assignment.spring.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class CoordEntity {
    @Column(name = "coord_lon")
    private Double lon;

    @Column(name = "coord_lat")
    private Double lat;
}
