package com.tarasenko.deliveryapp.rabbitmq.service;

import java.nio.charset.StandardCharsets;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DeliverCallback;
import com.tarasenko.deliveryapp.rabbitmq.config.RabbitMqConfig;
import com.tarasenko.deliveryapp.redis.service.WeatherForecastRedisService;

@Service
public class WeatherForecastRabbitSubscriber
{

  private final WeatherForecastRedisService weatherForecastRedisService;
  private final RabbitMqConfig rabbitMqConfig;

  @Autowired
  public WeatherForecastRabbitSubscriber(WeatherForecastRedisService weatherForecastRedisService,
                                         RabbitMqConfig rabbitMqConfig)
  {
    this.weatherForecastRedisService = weatherForecastRedisService;
    this.rabbitMqConfig = rabbitMqConfig;
  }

  public void subscribe() throws Exception {
    ConnectionFactory factory = new ConnectionFactory();
    factory.setHost(rabbitMqConfig.getHost());
    factory.setUsername(rabbitMqConfig.getUsername());
    factory.setPassword(rabbitMqConfig.getPassword());
    Connection connection = factory.newConnection();
    Channel channel = connection.createChannel();

    channel.exchangeDeclare(rabbitMqConfig.getExchangeName(), rabbitMqConfig.getExchangeType());
    String queueName = channel.queueDeclare().getQueue();
    channel.queueBind(queueName, rabbitMqConfig.getExchangeName(), "");

    DeliverCallback deliverCallback = (consumerTag, delivery) -> {
      String message = new String(delivery.getBody(), StandardCharsets.UTF_8);
      JsonObject jsonObject = new JsonParser().parse(message).getAsJsonObject();
      weatherForecastRedisService.saveWeatherForecast(jsonObject.toString());
    };
    channel.basicConsume(queueName, true, deliverCallback, consumerTag -> { });

  }

}
