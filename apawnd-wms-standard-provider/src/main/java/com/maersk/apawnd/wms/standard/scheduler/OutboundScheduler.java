package com.maersk.apawnd.wms.standard.scheduler;

import com.maersk.apawnd.commons.component.annotation.AutoLogging;
import com.maersk.apawnd.wms.standard.service.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class OutboundScheduler {

  private final OutboundService outboundService;

  public OutboundScheduler(OutboundService outboundService) {
    this.outboundService = outboundService;
  }

//  @Scheduled(cron = "0/5 * * * * ?")
  @AutoLogging
  public void execute() {
    outboundService.execute();
  }
}
