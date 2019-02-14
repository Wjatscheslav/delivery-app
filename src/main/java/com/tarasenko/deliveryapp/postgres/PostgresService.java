package com.tarasenko.deliveryapp.postgres;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.tarasenko.deliveryapp.postgres.entity.WeatherDocument;
import com.tarasenko.deliveryapp.postgres.repository.WeatherForecastRepository;
import com.tarasenko.deliveryapp.redis.service.WeatherForecastRedisService;

@Service
public class PostgresService
{

  private static final Logger LOGGER = LoggerFactory.getLogger(PostgresService.class);

  private static final Gson GSON = new Gson();

  private final WeatherForecastRepository weatherForecastRepository;
  private final WeatherForecastRedisService weatherForecastRedisService;

  @Autowired
  public PostgresService(WeatherForecastRepository weatherForecastRepository, WeatherForecastRedisService weatherForecastRedisService)
  {
    this.weatherForecastRepository = weatherForecastRepository;
    this.weatherForecastRedisService = weatherForecastRedisService;
  }

  public void postgresToRedis()
  {
    LOGGER.debug("Start saving data from postgres into refis");
    Iterable<WeatherDocument> weatherForecasts = weatherForecastRepository.findAll();
    weatherForecasts.forEach(weatherForecast ->
        weatherForecastRedisService.saveWeatherForecast(GSON.toJson(weatherForecast.getDocument())));
    LOGGER.debug("All data was successfully saved");
  }

}
