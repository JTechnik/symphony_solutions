package com.assignment.spring.persistence.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "weather")
@Data
public class WeatherEntity {

    @Id
    @SequenceGenerator(name = "weather_seq_generator", sequenceName = "weather_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather_seq_generator")
    private Integer id;

    @Column(name = "external_id")
    private Integer externalId;

    @Column(name = "main")
    private String main;

    @Column(name = "description")
    private String description;

    @Column(name = "icon")
    private String icon;

    @ManyToOne
    @JoinColumn(name = "weather_data_holder_id")
    private WeatherDataHolderEntity weatherDataHolder;
}
