package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.ApiEventMonitorModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ApiEventMonitorMapper {

  int updateMonitorStartByMonitorId(
      @Param("processId") String processId,
      @Param("monitorId") Integer monitorId);

  int updateMonitorRunningByEventName(
      @Param("eventName") String eventName);

  int updateMonitorEndByEventName(
      @Param("eventName") String eventName,
      @Param("lastRunResult") String lastRunResult,
      @Param("jobSleepSecond") Integer jobSleepSecond);

  int updateMonitorInitiation(
      @Param("currentStatus") String currentStatus);

  List<ApiEventMonitorModel> selectByCurrentStatus(
      @Param("currentStatus") String currentStatus);
}
