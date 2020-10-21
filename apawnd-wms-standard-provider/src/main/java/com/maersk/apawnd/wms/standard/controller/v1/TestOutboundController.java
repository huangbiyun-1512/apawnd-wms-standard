package com.maersk.apawnd.wms.standard.controller.v1;

import com.maersk.apawnd.commons.component.annotation.AutoLogging;
import com.maersk.apawnd.wms.standard.service.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("api/v1/outbound")
public class TestOutboundController {

  private final OutboundService outboundService;

  public TestOutboundController(OutboundService outboundService) {
    this.outboundService = outboundService;
  }

  @GetMapping(value = "/ack", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public void execute() {
    outboundService.execute();
  }

  @PostMapping(value = "/ack", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public void receiveAck(@RequestBody String payload) {
    log.info(payload);
  }
}
