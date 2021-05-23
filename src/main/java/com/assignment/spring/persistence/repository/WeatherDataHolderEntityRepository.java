package com.assignment.spring.persistence.repository;

import com.assignment.spring.persistence.entity.WeatherDataHolderEntity;
import org.springframework.data.repository.CrudRepository;

public interface WeatherDataHolderEntityRepository extends CrudRepository<WeatherDataHolderEntity, Integer> {
}
