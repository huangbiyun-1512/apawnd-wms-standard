package com.example.poc.service.impl;

import com.example.poc.component.util.MessageUtil;
import com.example.poc.service.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
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
//    log.warn("This is a waring log.");
//    log.error("This is a error log.");
//    log.debug("This is a debug log.");
//    log.trace("This is a trace log.");
//
//    // Retrieve value of I01-0001.code
//    messageUtil.getCode("I01-0001");
//    // Retrieve value of I01-0001.title
//    messageUtil.getTitle("I01-0001");
//    // Retrieve value of I01-0001.detail
//    messageUtil.getDetail("I01-0001");

  }

  @Cacheable(key = "#key")
  @Override
  public Object retrieveDataFromCacheIfExisted(String key) {
    return "cache value";
  }

  @CachePut(key = "#key")
  @Override
  public void updateCache(String key) {

  }

  @CacheEvict(key = "#key")
  @Override
  public void removeCache(String key) {

  }
}
