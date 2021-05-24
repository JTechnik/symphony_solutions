package com.assignment.spring.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Data
public class SysDto {

    private Integer type;

    private Integer externalId;

    private Double message;

    private String country;

    private Integer sunrise;

    private Integer sunset;
}
