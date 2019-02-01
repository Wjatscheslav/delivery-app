package com.tarasenko.deliveryapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.tarasenko.deliveryapp.rabbitmq.service.WeatherForecastRabbitSubscriber;

@SpringBootApplication
public class DeliveryAppApplication
{

  private final WeatherForecastRabbitSubscriber weatherForecastRabbitSubscriber;

  @Autowired
  public DeliveryAppApplication(WeatherForecastRabbitSubscriber weatherForecastRabbitSubscriber)
  {
    this.weatherForecastRabbitSubscriber = weatherForecastRabbitSubscriber;
  }

  public static void main(String[] args)
  {
    SpringApplication.run(DeliveryAppApplication.class, args);
  }

  @Bean
  public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
    return args -> {
      weatherForecastRabbitSubscriber.subscribe();
    };
  }

}

