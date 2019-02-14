package com.tarasenko.deliveryapp.rest.resource;

import static org.springframework.web.bind.annotation.RequestMethod.POST;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarasenko.deliveryapp.redis.service.WeatherForecastRedisService;

@RestController
public class RestWeatherResource
{

  private static final Logger LOGGER = LoggerFactory.getLogger(RestWeatherResource.class);

  private final WeatherForecastRedisService weatherForecastRedisService;

  @Autowired
  public RestWeatherResource(WeatherForecastRedisService weatherForecastRedisService)
  {
    this.weatherForecastRedisService = weatherForecastRedisService;
  }

  @RequestMapping(method = POST, path = "/weatherForecast", consumes = "application/json")
  public void getWeatherForecast(@RequestBody String forecast)
  {
    LOGGER.debug("Saving data to redis");
    weatherForecastRedisService.saveWeatherForecast(forecast);
    LOGGER.debug("Data was successfully saved");
  }

}
