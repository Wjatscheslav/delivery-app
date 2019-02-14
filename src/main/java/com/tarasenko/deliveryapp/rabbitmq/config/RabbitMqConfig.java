package com.tarasenko.deliveryapp.rabbitmq.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import com.tarasenko.deliveryapp.common.Config;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class RabbitMqConfig implements Config
{

  @Value("${rabbitmq.host}")
  private String host;

  @Value("${rabbitmq.port}")
  private int port;

  @Value("${rabbitmq.username}")
  private String username;

  @Value("${rabbitmq.password}")
  private String password;

  @Value("${rabbitmq.exchange.name}")
  private String exchangeName;

  @Value("${rabbitmq.exchange.type}")
  private String exchangeType;

}
