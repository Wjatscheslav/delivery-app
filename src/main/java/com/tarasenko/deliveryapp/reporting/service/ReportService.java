package com.tarasenko.deliveryapp.reporting.service;

import java.io.File;
import java.io.IOException;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.opencsv.CSVWriterBuilder;
import com.tarasenko.deliveryapp.elasticsearch.service.ElasticsearchService;
import com.tarasenko.deliveryapp.ftp.service.FtpService;
import com.tarasenko.deliveryapp.postgres.PostgresService;
import com.tarasenko.deliveryapp.redis.service.WeatherForecastRedisService;
import com.tarasenko.deliveryapp.reporting.config.ReportingConfig;
import com.tarasenko.deliveryapp.rest.service.RestService;

@Service
public class ReportService
{

  private final RestService restService;
  private final PostgresService postgresService;
  private final WeatherForecastRedisService weatherForecastRedisService;
  private final MessageService messageService;
  private final ReportingConfig reportingConfig;
  private final ElasticsearchService elasticsearchService;
  private final FtpService ftpService;

  @Autowired
  public ReportService(RestService restService, PostgresService postgresService,
                       WeatherForecastRedisService weatherForecastRedisService, MessageService messageService,
                       ReportingConfig reportingConfig, ElasticsearchService elasticsearchService,
                       FtpService ftpService)
  {
    this.restService = restService;
    this.postgresService = postgresService;
    this.weatherForecastRedisService = weatherForecastRedisService;
    this.messageService = messageService;
    this.reportingConfig = reportingConfig;
    this.elasticsearchService = elasticsearchService;
    this.ftpService = ftpService;
  }

  public void sendReport() throws Exception
  {
    prepareData();
    Set<String> weatherForecasts = weatherForecastRedisService.readWeatherForecasts();
    writeToCsv(weatherForecasts);
    messageService.sendMessageWithAttachment();
    removeTempFile();
  }

  private void writeToCsv(Set<String> weatherForecasts) throws IOException
  {
    List<String[]> strings = weatherForecasts.stream()
        .map(s -> s.split(","))
        .collect(Collectors.toList());
    try (Writer writer = Files.newBufferedWriter(Paths.get(reportingConfig.getTmpFilePath())))
    {
      new CSVWriterBuilder(writer).withSeparator(',')
          .build()
          .writeAll(strings);
    }
  }

  private void removeTempFile()
  {
    File file = new File(reportingConfig.getTmpFilePath());
    file.delete();
  }

  private void prepareData() throws Exception
  {
//    restService.restToRedis();
    postgresService.postgresToRedis();
    elasticsearchService.elasticToRedis();
    ftpService.ftpToRedis();
  }

}
