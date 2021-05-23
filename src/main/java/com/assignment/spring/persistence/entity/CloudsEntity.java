package com.assignment.spring.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class CloudsEntity {
    @Column(name = "clouds_all")
    private Integer all;
}
