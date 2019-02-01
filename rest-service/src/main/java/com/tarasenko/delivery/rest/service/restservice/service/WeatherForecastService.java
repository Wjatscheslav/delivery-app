package com.tarasenko.delivery.rest.service.restservice.service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.stereotype.Service;

import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;
import com.tarasenko.delivery.rest.service.restservice.pojo.WeatherForecast;

@Service
public class WeatherForecastService
{

  public List<WeatherForecast> readWeatherForecastsFromFile(Path filePath) throws IOException
  {
    CsvToBean<WeatherForecast> csvToBean;
    try (Reader reader = Files.newBufferedReader(filePath))
    {
      csvToBean = new CsvToBeanBuilder(reader)
          .withType(WeatherForecast.class)
          .withIgnoreLeadingWhiteSpace(true)
          .withSeparator(';')
          .build();
      return csvToBean.parse();
    }
  }

}
