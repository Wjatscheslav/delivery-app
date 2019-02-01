package com.tarasenko.deliveryapp.ftp.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@Getter
@Setter
public class FtpClientConfig
{

  @Value("${ftp.host}")
  private String ftpHost;
  @Value("${ftp.port}")
  private Integer ftpPort;
  @Value("${ftp.login}")
  private String ftpLogin;
  @Value("${ftp.password}")
  private String ftpPassword;
  @Value("${ftp.file.path}")
  private String ftpFilePath;

}
