package com.assignment.spring.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Data
public class SysEntity {
    @Column(name = "sys_type")
    private Integer type;

    @Column(name = "sys_external_id")
    private Integer externalId;

    @Column(name = "sys_message")
    private Double message;

    @Column(name = "sys_country")
    private String country;

    @Column(name = "sys_sunrise")
    private Integer sunrise;

    @Column(name = "sys_sunset")
    private Integer sunset;
}
