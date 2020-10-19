package com.maersk.apawnd.wms.standard.service.impl;

import com.maersk.apawnd.wms.standard.mapper.EventQueueApiMapper;
import com.maersk.apawnd.wms.standard.model.EventQueueApiModel;
import com.maersk.apawnd.wms.standard.service.EventQueueService;
import com.maersk.apawnd.wms.standard.service.config.EventQueueServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@Service
public class EventQueueServiceImpl implements EventQueueService {

  private final EventQueueServiceConfig eventQueueServiceConfig;
  private final EventQueueApiMapper eventQueueApiMapper;

  public EventQueueServiceImpl(
      EventQueueServiceConfig eventQueueServiceConfig,
      EventQueueApiMapper eventQueueApiMapper) {
    this.eventQueueServiceConfig = eventQueueServiceConfig;
    this.eventQueueApiMapper = eventQueueApiMapper;
  }

  @Override
  @Transactional
  public List<EventQueueApiModel> retrieve(String eventName) {
    return eventQueueApiMapper.selectByEventName(
        eventName,
        eventQueueServiceConfig.getBatchQueueCount(),
        eventQueueServiceConfig.getEventType(),
        eventQueueServiceConfig.getRetryCount(),
        eventQueueServiceConfig.getTimeoutRetryMinute());
  }

  @Override
  @Transactional
  public int updateStatusStartByFifoSequence(Long fifoSequence) {
    return eventQueueApiMapper.updateStatusStartByFifoSequence(fifoSequence);
  }

  @Override
  @Transactional
  public int updateStatusFinishedByFifoSequence(Long fifoSequence) {
    return eventQueueApiMapper.updateStatusFinishedByFifoSequence(fifoSequence);
  }
}
