package com.tarasenko.deliveryapp.ftp.service;

import java.io.InputStream;

import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

  private static final Logger LOGGER = LoggerFactory.getLogger(FtpService.class);

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
    LOGGER.debug("Start saving data from ftp to redis " + ftpClientConfig.toJsonString());
    var ftpClient = ftpClientProvider.getFtpClient();
    try
    {
      ftpClient.connect(ftpClientConfig.getFtpHost(), ftpClientConfig.getFtpPort());
      ftpClient.login(ftpClientConfig.getFtpLogin(), ftpClientConfig.getFtpPassword());
      ftpClient.changeWorkingDirectory("weather");
      FTPFile[] files = ftpClient.listFiles();
      for (FTPFile file : files)
      {
        InputStream inputStream = ftpClient.retrieveFileStream(file.getName());
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode jsonNode = objectMapper.readTree(inputStream);

        for (JsonNode jsonNodeEl : jsonNode)
        {
          weatherForecastRedisService.saveWeatherForecast(jsonNodeEl.asText());
        }
      }
      LOGGER.debug("Data was successfully saved into redis " + ftpClientConfig.toJsonString());
    }
    catch (Exception e)
    {
      LOGGER.error("Error occurred during saving data from FTP to redis " + ftpClientConfig.toJsonString());
    }
  }


}
