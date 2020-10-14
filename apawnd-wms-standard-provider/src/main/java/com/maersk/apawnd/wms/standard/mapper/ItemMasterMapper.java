package com.maersk.apawnd.wms.standard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ItemMasterMapper {

  int selectCountByWhIdAndItemNumber(
      @Param("whId") String whId,
      @Param("itemNumber") String itemNumber);
}
