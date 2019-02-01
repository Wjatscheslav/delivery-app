package com.tarasenko.deliveryapp.redis.service;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonArray;
import com.tarasenko.deliveryapp.redis.config.RedisConfig;

import redis.clients.jedis.Jedis;

@Service
public class WeatherForecastRedisService
{

  private final RedisConfig redisConfig;

  @Autowired
  public WeatherForecastRedisService(RedisConfig redisConfig)
  {
    this.redisConfig = redisConfig;
  }

  public void saveWeatherForecasts(JsonArray jsonArray)
  {
    Jedis jedis = getRedisClient();
    for (var i = 0; i < jsonArray.size(); i++) {
      jedis.sadd(redisConfig.getWeatherKey(), jsonArray.get(i).toString());
    }
  }

  public void saveWeatherForecast(String weatherForecast)
  {
    Jedis jedis = getRedisClient();
    jedis.sadd(redisConfig.getWeatherKey(), weatherForecast);
  }

  public Set<String> readWeatherForecasts()
  {
    Jedis jedis = getRedisClient();
    return jedis.smembers(redisConfig.getWeatherKey());
  }

  public void deleteRedisForecasts()
  {
    Jedis jedis = getRedisClient();
    jedis.del(redisConfig.getWeatherKey());
  }

  private Jedis getRedisClient()
  {
    return new Jedis(redisConfig.getHost(), redisConfig.getPort());
  }

}
