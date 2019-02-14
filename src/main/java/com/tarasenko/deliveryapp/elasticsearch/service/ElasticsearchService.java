package com.tarasenko.deliveryapp.elasticsearch.service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.stream.Stream;

import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.Client;
import org.elasticsearch.common.settings.Settings;
import org.elasticsearch.common.transport.TransportAddress;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.transport.client.PreBuiltTransportClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarasenko.deliveryapp.elasticsearch.config.ElasticsearchConfig;
import com.tarasenko.deliveryapp.redis.service.WeatherForecastRedisService;

@Service
public class ElasticsearchService
{

  private static final Logger LOGGER = LoggerFactory.getLogger(ElasticsearchService.class);

  private final WeatherForecastRedisService weatherForecastRedisService;
  private final ElasticsearchConfig elasticsearchConfig;

  @Autowired
  public ElasticsearchService(WeatherForecastRedisService weatherForecastRedisService,
                              ElasticsearchConfig elasticsearchConfig)
  {
    this.weatherForecastRedisService = weatherForecastRedisService;
    this.elasticsearchConfig = elasticsearchConfig;
  }

  public void elasticToRedis() throws UnknownHostException
  {
    LOGGER.debug("Start getting data from elasticsearch " + elasticsearchConfig.toJsonString());
    Settings settings = Settings.builder()
        .put("cluster.name", elasticsearchConfig.getElasticsearchClusterName())
        .put("client.transport.ignore_cluster_name", true)
        .build();
    System.setProperty("es.set.netty.runtime.available.processors", "false");
    Client client = new PreBuiltTransportClient(settings)
        .addTransportAddress(new TransportAddress(
            InetAddress.getByName(elasticsearchConfig.getElasticsearchHost()), elasticsearchConfig.getElasticsearchPort()));
    SearchResponse searchResponse = client.prepareSearch().execute().actionGet();
    LOGGER.debug("Start saving data from elasticsearch into redis " + elasticsearchConfig.toJsonString());
    Stream.of(searchResponse.getHits().getHits())
        .filter(searchHit -> elasticsearchConfig.getElasticsearchWeatherIndex().equals(searchHit.getIndex()))
        .map(SearchHit::getSourceAsString)
        .forEach(weatherForecastRedisService::saveWeatherForecast);
    LOGGER.debug("Data was successfully saved into redis " + elasticsearchConfig.toJsonString());
  }

}
