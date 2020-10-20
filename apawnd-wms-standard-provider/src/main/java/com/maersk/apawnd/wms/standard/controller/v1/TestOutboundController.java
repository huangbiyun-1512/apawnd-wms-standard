package com.maersk.apawnd.wms.standard.controller.v1;

import com.maersk.apawnd.commons.component.annotation.AutoLogging;
import com.maersk.apawnd.commons.component.dto.BaseResponseDto;
import com.maersk.apawnd.wms.standard.dto.AsnDto;
import com.maersk.apawnd.wms.standard.service.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
  public ResponseEntity sendGrn(
      @RequestParam(name = "event_name") String eventName) {
    outboundService.sendAck(eventName);
    return ResponseEntity.ok(BaseResponseDto.ok());
  }
}
