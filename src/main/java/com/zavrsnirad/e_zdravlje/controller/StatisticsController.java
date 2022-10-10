package com.zavrsnirad.e_zdravlje.controller;

import com.zavrsnirad.e_zdravlje.dto.stats.AllStats;
import com.zavrsnirad.e_zdravlje.service.StatisticsService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class StatisticsController {
  private final StatisticsService statisticsService;

  public StatisticsController(StatisticsService statisticsService) {
    this.statisticsService = statisticsService;
  }

  @RequestMapping(value = "/statistika")
  @ResponseBody
  public AllStats getAllStats() {
    return statisticsService.getAllStats();
  }
}
