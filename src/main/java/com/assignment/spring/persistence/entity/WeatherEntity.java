package com.assignment.spring.persistence.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "weather")
@Data
public class WeatherEntity {

    @Id
    @SequenceGenerator(name = "weather_seq_generator", sequenceName = "weather_id_sequence", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "weather_seq_generator")
    private Integer id;

    private String city;

    private String country;

    private Double temperature;
}
