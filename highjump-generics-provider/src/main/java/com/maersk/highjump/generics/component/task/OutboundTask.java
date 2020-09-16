package com.maersk.highjump.generics.component.task;

import com.maersk.highjump.generics.service.OutboundService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OutboundTask {

  private final OutboundService outboundService;

  public OutboundTask(
      OutboundService outboundService) {
    this.outboundService = outboundService;
  }

  @Scheduled(cron = "0/5 * * * * *")
  public void execute() {
    outboundService.scaLoadedAck();
  }
}
