package com.maersk.apawnd.wms.standard.service.impl;

import com.maersk.apawnd.wms.standard.mapper.ApiEventMonitorMapper;
import com.maersk.apawnd.wms.standard.mapper.EventQueueApiMapper;
import com.maersk.apawnd.wms.standard.model.ApiEventMonitorModel;
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
  private final ApiEventMonitorMapper apiEventMonitorMapper;

  public EventQueueServiceImpl(
      EventQueueServiceConfig eventQueueServiceConfig,
      EventQueueApiMapper eventQueueApiMapper,
      ApiEventMonitorMapper apiEventMonitorMapper) {
    this.eventQueueServiceConfig = eventQueueServiceConfig;
    this.eventQueueApiMapper = eventQueueApiMapper;
    this.apiEventMonitorMapper = apiEventMonitorMapper;
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

  @Override
  @Transactional
  public int updateErrorByFifoSequence(EventQueueApiModel eventQueueApiModel, String message) {
    boolean isMailAlert = eventQueueServiceConfig.isMailAlert() &&
        eventQueueApiModel.getRetryCount() >= eventQueueServiceConfig.getRetryCount() - 1;
    String status = isMailAlert ? "MAIL" : "ERROR";

    return eventQueueApiMapper.updateErrorByFifoSequence(
        eventQueueApiModel.getFifoSequence(), status, message);
  }

  @Override
  @Transactional
  public List<ApiEventMonitorModel> retrieveEventMonitorByCurrentStatus(String currentStatus) {
    return apiEventMonitorMapper.selectByCurrentStatus(currentStatus);
  }

  @Override
  @Transactional
  public int updateMonitorStartByMonitorId(String processId, Integer monitorId) {
    return apiEventMonitorMapper.updateMonitorStartByMonitorId(processId, monitorId);
  }

  @Override
  @Transactional
  public int updateMonitorEndByEventName(String eventName, String lastRunResult) {
    return apiEventMonitorMapper.updateMonitorEndByEventName(
        eventName, lastRunResult, eventQueueServiceConfig.getJobSleepSecond());
  }

  @Override
  @Transactional
  public int initMonitorStatus(String currentStatus) {
    return apiEventMonitorMapper.updateMonitorInitiation(currentStatus);
  }
}
