package com.maersk.apawnd.wms.standard.service.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Data
public class EventQueueServiceConfig {

  @Value("${biz.event-queue-service.config.batch-queue-count}")
  private Integer batchQueueCount;

  @Value("${biz.event-queue-service.config.event-type}")
  private String eventType;

  @Value("${biz.event-queue-service.config.retry-count}")
  private Integer retryCount;

  @Value("${biz.event-queue-service.config.timeout-retry-minute}")
  private Integer timeoutRetryMinute;

  @Value("${biz.event-queue-service.config.mail-alert}")
  private boolean mailAlert;

  @Value("${biz.event-queue-service.config.job-sleep-second}")
  private Integer jobSleepSecond;
}
