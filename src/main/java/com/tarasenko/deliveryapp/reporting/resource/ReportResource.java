package com.tarasenko.deliveryapp.reporting.resource;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tarasenko.deliveryapp.reporting.service.ReportService;

@RestController
public class ReportResource
{

  private final ReportService reportService;

  @Autowired
  public ReportResource(ReportService reportService)
  {
    this.reportService = reportService;
  }

  @RequestMapping(method = GET, path = "/report", produces = "application/json")
  public void generateReport() throws Exception
  {
    reportService.sendReport();
  }

}
