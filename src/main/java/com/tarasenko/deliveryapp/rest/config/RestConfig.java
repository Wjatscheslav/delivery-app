package com.tarasenko.deliveryapp.rest.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class RestConfig
{

  @Value("${rest.weather.uri}")
  private String uri;

}
