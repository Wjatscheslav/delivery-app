package com.tarasenko.deliveryapp.ftp.service;

import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tarasenko.deliveryapp.ftp.config.FtpClientConfig;
import com.tarasenko.deliveryapp.ftp.provider.FtpClientProvider;
import com.tarasenko.deliveryapp.redis.service.WeatherForecastRedisService;

@Service
public class FtpService
{

  private final FtpClientProvider ftpClientProvider;
  private final FtpClientConfig ftpClientConfig;
  private final WeatherForecastRedisService weatherForecastRedisService;

  @Autowired
  public FtpService(FtpClientProvider ftpClientProvider, FtpClientConfig ftpClientConfig,
                    WeatherForecastRedisService weatherForecastRedisService)
  {
    this.ftpClientProvider = ftpClientProvider;
    this.ftpClientConfig = ftpClientConfig;
    this.weatherForecastRedisService = weatherForecastRedisService;
  }

  public void ftpToRedis()
  {
    var ftpClient = ftpClientProvider.getFtpClient();
    try
    {
      ftpClient.connect(ftpClientConfig.getFtpHost(), ftpClientConfig.getFtpPort());
      ftpClient.login(ftpClientConfig.getFtpLogin(), ftpClientConfig.getFtpPassword());
      InputStream inputStream = ftpClient.retrieveFileStream(ftpClientConfig.getFtpFilePath());
      ObjectMapper objectMapper = new ObjectMapper();
      JsonNode jsonNode = objectMapper.readTree(inputStream);

      for (JsonNode jsonNodeEl : jsonNode)
      {
        weatherForecastRedisService.saveWeatherForecast(jsonNodeEl.asText());
      }
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }


}
