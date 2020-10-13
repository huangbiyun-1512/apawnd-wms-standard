package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.EventQueueApiModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EventQueueApiMapper {

  List<EventQueueApiModel> selectByEventName(
      @Param("eventName") String eventName,
      @Param("batchQueueCount") Integer batchQueueCount,
      @Param("eventType") String eventType,
      @Param("retryCount") Integer retryCount,
      @Param("timeoutRetryMinute") Integer timeoutRetryMinute);

  int updateStatusStartByFifoSequence(
      @Param("fifoSequence") Long fifoSequence);

  int updateStatusFinishedByFifoSequence(
      @Param("fifoSequence") Long fifoSequence);
}
