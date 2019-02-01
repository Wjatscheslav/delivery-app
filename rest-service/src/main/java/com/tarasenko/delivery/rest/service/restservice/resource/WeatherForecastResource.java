package com.tarasenko.delivery.rest.service.restservice.resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarasenko.delivery.rest.service.restservice.pojo.WeatherForecast;
import com.tarasenko.delivery.rest.service.restservice.service.WeatherForecastService;

@RestController
public class WeatherForecastResource
{

  private static final Path FILE_PATH = Paths.get("/Users/viacheslav/Documents/Work/Connecture/pure-exemplar/delivery-app/rest-service/src/main/resources/rest-weather-history.csv");


  private final WeatherForecastService weatherForecastService;

  @Autowired
  public WeatherForecastResource(WeatherForecastService weatherForecastService)
  {
    this.weatherForecastService = weatherForecastService;
  }

  @RequestMapping(method = GET, path = "/weatherForecast", produces = "application/json")
  public List<WeatherForecast> getWeatherForecast() throws IOException
  {
    return weatherForecastService.readWeatherForecastsFromFile(FILE_PATH);
  }

}
