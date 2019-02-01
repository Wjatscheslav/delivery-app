package com.tarasenko.delivery.rest.service.restservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.tarasenko.delivery"})
public class RestServiceApplication
{

  public static void main(String[] args)
  {
    SpringApplication.run(RestServiceApplication.class, args);
  }

}

