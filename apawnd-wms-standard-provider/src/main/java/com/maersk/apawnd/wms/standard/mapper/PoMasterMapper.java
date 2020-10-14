package com.maersk.apawnd.wms.standard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface PoMasterMapper {

  int selectCountByWhIdAndPoNumber(
      @Param("whId") String whId,
      @Param("poNumber") String poNumber);
}
