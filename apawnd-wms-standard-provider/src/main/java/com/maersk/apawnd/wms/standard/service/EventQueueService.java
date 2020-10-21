package com.maersk.apawnd.wms.standard.service;

import com.maersk.apawnd.wms.standard.model.ApiEventMonitorModel;
import com.maersk.apawnd.wms.standard.model.EventQueueApiModel;

import java.util.List;

public interface EventQueueService {

  List<EventQueueApiModel> retrieve(String eventName);
  int updateStatusStartByFifoSequence(Long fifoSequence);
  int updateStatusFinishedByFifoSequence(Long fifoSequence);
  int updateErrorByFifoSequence(EventQueueApiModel eventQueueApiModel, String message);
  List<ApiEventMonitorModel> retrieveEventMonitorByCurrentStatus(String currentStatus);
  int updateMonitorStartByMonitorId(String processId, Integer monitorId);
  int updateMonitorRunningByEventName(String eventName);
  int updateMonitorEndByEventName(String eventName, String lastRunResult);
  int initMonitorStatus(String currentStatus);
}
