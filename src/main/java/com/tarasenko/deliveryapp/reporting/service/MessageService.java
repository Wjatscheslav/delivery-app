package com.tarasenko.deliveryapp.reporting.service;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tarasenko.deliveryapp.reporting.config.ReportingConfig;

@Service
public class MessageService
{

  private final ReportingConfig reportingConfig;

  @Autowired
  public MessageService(ReportingConfig reportingConfig)
  {
    this.reportingConfig = reportingConfig;
  }

  public void sendMessageWithAttachment()
  {
    Properties props = new Properties();
    props.put("mail.smtp.auth", reportingConfig.getAuthRequired());
    props.put("mail.smtp.starttls.enable", reportingConfig.getTlsEnabled());
    props.put("mail.smtp.host", reportingConfig.getSmtpHost());
    props.put("mail.smtp.port", reportingConfig.getSmtpPort());

    Session session = Session.getInstance(props,
        new javax.mail.Authenticator() {
          protected PasswordAuthentication getPasswordAuthentication() {
            return new PasswordAuthentication(reportingConfig.getUsername(), reportingConfig.getPassword());
          }
        });

    try
    {
      Message message = new MimeMessage(session);
      message.setFrom(new InternetAddress(reportingConfig.getFrom()));
      message.setRecipients(Message.RecipientType.TO,
          InternetAddress.parse(reportingConfig.getTo()));
      message.setSubject(reportingConfig.getSubject());
      BodyPart messageBodyPart = new MimeBodyPart();
      messageBodyPart.setText(reportingConfig.getText());
      Multipart multipart = new MimeMultipart();
      multipart.addBodyPart(messageBodyPart);
      messageBodyPart = new MimeBodyPart();
      DataSource source = new FileDataSource(reportingConfig.getTmpFilePath());
      messageBodyPart.setDataHandler(new DataHandler(source));
      messageBodyPart.setFileName(reportingConfig.getTmpFilePath());
      multipart.addBodyPart(messageBodyPart);
      message.setContent(multipart);
      Transport.send(message);
    }
    catch (MessagingException e)
    {
      throw new RuntimeException(e);
    }
  }

}
