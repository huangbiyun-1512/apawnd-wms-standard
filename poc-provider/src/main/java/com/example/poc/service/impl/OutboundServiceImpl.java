package com.example.poc.service.impl;

import com.example.poc.service.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class OutboundServiceImpl implements OutboundService {

  @Override
  public void scaLoadedAck() {
    log.info("SCA loaded ack executing...");
  }
}
