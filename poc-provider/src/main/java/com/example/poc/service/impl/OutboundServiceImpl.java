package com.example.poc.service.impl;

import com.example.poc.component.util.MessageUtil;
import com.example.poc.service.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OutboundServiceImpl implements OutboundService {

  private final MessageUtil messageUtil;

  public OutboundServiceImpl(MessageUtil messageUtil) {
    this.messageUtil = messageUtil;
  }

  @Override
  public void scaLoadedAck() {
    log.info("SCA loaded ack executing...");

    // Retrieve value of I01-0001.code
    messageUtil.getCode("I01-0001");
    // Retrieve value of I01-0001.title
    messageUtil.getTitle("I01-0001");
    // Retrieve value of I01-0001.detail
    messageUtil.getDetail("I01-0001");

  }
}
