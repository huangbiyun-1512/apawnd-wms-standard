package com.maersk.apawnd.wms.standard.service;

import com.maersk.apawnd.wms.standard.model.EventQueueApiModel;

import java.util.List;

public interface EventQueueService {

  List<EventQueueApiModel> retrieve(String eventName);

  int updateStatusStartByFifoSequence(Long fifoSequence);

  int updateStatusFinishedByFifoSequence(Long fifoSequence);
}
