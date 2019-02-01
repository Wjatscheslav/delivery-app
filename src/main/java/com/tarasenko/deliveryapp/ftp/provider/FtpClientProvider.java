package com.tarasenko.deliveryapp.ftp.provider;

import org.apache.commons.net.ftp.FTPClient;
import org.springframework.stereotype.Component;

@Component
public class FtpClientProvider
{

  public FTPClient getFtpClient()
  {
    return new FTPClient();
  }

}
