package com.tarasenko.deliveryapp.redis.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class RedisConfig
{

  @Value("${redis.host}")
  private String host;

  @Value("${redis.port}")
  private int port;

  @Value("${redis.weather.key}")
  private String weatherKey;


}
