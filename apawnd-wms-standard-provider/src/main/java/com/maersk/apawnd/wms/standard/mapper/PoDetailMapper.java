package com.maersk.apawnd.wms.standard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PoDetailMapper {

  int selectCountByWhIdAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
      @Param("whId") String whId,
      @Param("poNumber") String poNumber,
      @Param("lineNumber") String lineNumber,
      @Param("itemNumber") String itemNumber,
      @Param("scheduleNumber") String scheduleNumber);
}
