package com.tarasenko.deliveryapp.elasticsearch.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class ElasticsearchConfig
{

  @Value("${elasticsearch.host}")
  private String elasticsearchHost;

  @Value("${elasticsearch.port}")
  private int elasticsearchPort;

  @Value("${elasticsearch.cluster.name}")
  private String elasticsearchClusterName;

  @Value("${elasticsearch.weather.index}")
  private String elasticsearchWeatherIndex;

}
