package com.tarasenko.delivery.rabbitmq.resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeoutException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarasenko.delivery.rabbitmq.publisher.WeatherForecastPublisher;

@RestController
public class RabbitPublisherResource
{

  private final WeatherForecastPublisher weatherForecastPublisher;


  @Autowired
  public RabbitPublisherResource(WeatherForecastPublisher weatherForecastPublisher)
  {
    this.weatherForecastPublisher = weatherForecastPublisher;
  }

  @RequestMapping(method = GET, path = "/publishMessages", produces = "application/json")
  public List<String> publishMessages() throws InterruptedException, TimeoutException, IOException
  {
    weatherForecastPublisher.publishMessages();
    return List.of();
  }
}
