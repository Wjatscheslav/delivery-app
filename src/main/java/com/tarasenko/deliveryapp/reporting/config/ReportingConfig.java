package com.tarasenko.deliveryapp.reporting.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class ReportingConfig
{

  @Value("${reporting.mail.to}")
  private String to;

  @Value("${reporting.mail.from}")
  private String from;

  @Value("${reporting.mail.subject}")
  private String subject;

  @Value("${reporting.mail.text}")
  private String text;

  @Value("${reporting.mail.tmp.file.path}")
  private String tmpFilePath;

  @Value("${reporting.smtp.host}")
  private String smtpHost;

  @Value("${reporting.smtp.port}")
  private String smtpPort;

  @Value("${reporting.smtp.auth.required}")
  private Boolean authRequired;

  @Value("${reporting.smtp.username}")
  private String username;

  @Value("${reporting.smtp.password}")
  private String password;

  @Value("${reporting.smtp.tls.enabled}")
  private String tlsEnabled;

}
