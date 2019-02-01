package com.tarasenko.delivery.rest.service.restservice.pojo;

import com.opencsv.bean.CsvBindByName;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class WeatherForecast
{

  private long id;
  @CsvBindByName
  private String date;
  @CsvBindByName
  private double temperatureMin;
  @CsvBindByName
  private double temperatureMax;
  @CsvBindByName
  private double precipitation;
  @CsvBindByName
  private double snowFall;
  @CsvBindByName
  private double snowDepth;
  @CsvBindByName
  private double avgWindSpeed;
  @CsvBindByName
  private double fastest2MinWindDir;
  @CsvBindByName
  private double fastest2MinWindSpeed;
  @CsvBindByName
  private double fastest5SecWindDir;
  @CsvBindByName
  private double fastest5SecWindSpeed;
  @CsvBindByName
  private String fog;
  @CsvBindByName
  private String fogHeavy;
  @CsvBindByName
  private String mist;
  @CsvBindByName
  private String rain;
  @CsvBindByName
  private String fogGround;
  @CsvBindByName
  private String ice;
  @CsvBindByName
  private String glaze;
  @CsvBindByName
  private String drizzle;
  @CsvBindByName
  private String snow;
  @CsvBindByName
  private String freezingRain;
  @CsvBindByName
  private String smokeHaze;
  @CsvBindByName
  private String thunder;
  @CsvBindByName
  private String highWind;
  @CsvBindByName
  private String hail;
  @CsvBindByName
  private String blowingSnow;
  @CsvBindByName
  private String dust;
  @CsvBindByName
  private String freezingFog;

}
