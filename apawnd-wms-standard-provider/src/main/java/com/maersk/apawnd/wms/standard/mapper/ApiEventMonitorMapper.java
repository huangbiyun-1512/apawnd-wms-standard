package com.maersk.apawnd.wms.standard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ApiEventMonitorMapper {

  int updateMonitorEndByEventName(
      @Param("eventName") String eventName,
      @Param("lastRunResult") String lastRunResult,
      @Param("jobSleepSecond") Integer jobSleepSecond);
}
