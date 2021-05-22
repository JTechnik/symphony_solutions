package com.assignment.spring.persistence.reposotory;

import com.assignment.spring.persistence.entity.WeatherEntity;
import org.springframework.data.repository.CrudRepository;

public interface WeatherRepository extends CrudRepository<WeatherEntity, Integer> {
}
