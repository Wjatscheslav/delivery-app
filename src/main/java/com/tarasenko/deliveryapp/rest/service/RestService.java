package com.tarasenko.deliveryapp.rest.service;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.google.gson.JsonParser;
import com.tarasenko.deliveryapp.redis.service.WeatherForecastRedisService;
import com.tarasenko.deliveryapp.rest.config.RestConfig;

@Service
public class RestService
{

  private final WeatherForecastRedisService weatherForecastRedisService;
  private final RestConfig restConfig;

  @Autowired
  public RestService(WeatherForecastRedisService weatherForecastRedisService, RestConfig restConfig)
  {
    this.weatherForecastRedisService = weatherForecastRedisService;
    this.restConfig = restConfig;
  }

  public void restToRedis() throws URISyntaxException, IOException, InterruptedException
  {
    HttpRequest request = HttpRequest.newBuilder()
        .uri(new URI(restConfig.getUri()))
        .GET()
        .build();

    HttpResponse<String> response = HttpClient.newHttpClient()
        .send(request, HttpResponse.BodyHandlers.ofString());

    JsonArray jsonArray = new JsonParser().parse(response.body()).getAsJsonArray();
    weatherForecastRedisService.saveWeatherForecasts(jsonArray);
  }

}
