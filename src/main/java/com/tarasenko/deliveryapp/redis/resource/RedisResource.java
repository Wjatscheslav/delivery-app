package com.tarasenko.deliveryapp.redis.resource;

import static org.springframework.web.bind.annotation.RequestMethod.DELETE;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarasenko.deliveryapp.redis.service.WeatherForecastRedisService;

@RestController
public class RedisResource
{

  private final WeatherForecastRedisService weatherForecastRedisService;

  @Autowired
  public RedisResource(WeatherForecastRedisService weatherForecastRedisService)
  {
    this.weatherForecastRedisService = weatherForecastRedisService;
  }

  @RequestMapping(method = GET, path = "/redis/weatherForecasts", produces = "application/json")
  public Set<String> getWeatherForecasts()
  {
    return weatherForecastRedisService.readWeatherForecasts();
  }

  @RequestMapping(method = DELETE, path = "/redis/weatherForecasts", produces = "application/json")
  public void deleteWeatherForecasts()
  {
    weatherForecastRedisService.deleteRedisForecasts();
  }

}
