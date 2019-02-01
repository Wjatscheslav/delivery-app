package com.tarasenko.delivery.rabbitmq.publisher;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.tarasenko.delivery.rest.service.restservice.pojo.WeatherForecast;
import com.tarasenko.delivery.rest.service.restservice.service.WeatherForecastService;

@Service
public class WeatherForecastPublisher
{

  private static final Path FILE_PATH = Paths.get("/Users/viacheslav/Documents/Work/Connecture/pure-exemplar/delivery-app/rest-service/src/main/resources/rabbit-weather-history.csv");

  private final WeatherForecastService weatherForecastService;

  private static final String EXCHANGE_NAME = "weather";
  private static final String EXCHANGE_TYPE = "fanout";
  private static final Gson GSON = new Gson();


  @Autowired
  public WeatherForecastPublisher(WeatherForecastService weatherForecastService)
  {
    this.weatherForecastService = weatherForecastService;
  }

  public void publishMessages() throws IOException, TimeoutException, InterruptedException
  {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost("localhost");
    factory.setUsername("rabbitmq");
    factory.setPassword("rabbitmq");
    try (Connection connection = factory.newConnection();
         Channel channel = connection.createChannel()) {
      channel.exchangeDeclare(EXCHANGE_NAME, EXCHANGE_TYPE);

      List<WeatherForecast> weatherForecasts = weatherForecastService.readWeatherForecastsFromFile(FILE_PATH);

      for (WeatherForecast weatherForecast : weatherForecasts) {
        channel.basicPublish(EXCHANGE_NAME, "", null, GSON.toJson(weatherForecast).getBytes(StandardCharsets.UTF_8));
        System.out.println(" [x] Sent '" + weatherForecast.toString() + "'");
        Thread.sleep(3000);
      }
    }
  }

}
