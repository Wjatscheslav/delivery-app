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
@Table(name = "forecast")
@Getter
@Setter
public class WeatherDocument
{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "forecast_id")
  private long id;

  @Column(name = "forecast")
  private String document;
}
