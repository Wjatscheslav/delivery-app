package com.tarasenko.deliveryapp.postgres.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "weather_forecast")
@Getter
@Setter
public class WeatherForecast
{

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  @Column(name = "date")
  private String date;

  @Column(name = "temperatureMin")
  private double temperatureMin;

  @Column(name = "temperatureMax")
  private double temperatureMax;

  @Column(name = "precipitation")
  private double precipitation;

  @Column(name = "snowFall")
  private double snowFall;

  @Column(name = "snowDepth")
  private double snowDepth;

  @Column(name = "avgWindSpeed")
  private double avgWindSpeed;

  @Column(name = "fastest2MinWindDir")
  private double fastest2MinWindDir;

  @Column(name = "fastest2MinWindSpeed")
  private double fastest2MinWindSpeed;

  @Column(name = "fastest5SecWindDir")
  private double fastest5SecWindDir;

  @Column(name = "fastest5SecWindSpeed")
  private double fastest5SecWindSpeed;

  @Column(name = "fog")
  private String fog;

  @Column(name = "fogHeavy")
  private String fogHeavy;

  @Column(name = "mist")
  private String mist;

  @Column(name = "rain")
  private String rain;

  @Column(name = "fogGround")
  private String fogGround;

  @Column(name = "ice")
  private String ice;

  @Column(name = "glaze")
  private String glaze;

  @Column(name = "drizzle")
  private String drizzle;

  @Column(name = "snow")
  private String snow;

  @Column(name = "freezingRain")
  private String freezingRain;

  @Column(name = "smokeHaze")
  private String smokeHaze;

  @Column(name = "thunder")
  private String thunder;

  @Column(name = "highWind")
  private String highWind;

  @Column(name = "hail")
  private String hail;

  @Column(name = "blowingSnow")
  private String blowingSnow;

  @Column(name = "dust")
  private String dust;

  @Column(name = "freezingFog")
  private String freezingFog;


}
