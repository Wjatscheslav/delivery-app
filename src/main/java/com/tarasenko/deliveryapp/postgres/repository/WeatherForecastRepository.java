package com.tarasenko.deliveryapp.postgres.repository;

import org.springframework.data.repository.CrudRepository;

import com.tarasenko.deliveryapp.postgres.entity.WeatherDocument;

public interface WeatherForecastRepository extends CrudRepository<WeatherDocument, Long>
{

}
