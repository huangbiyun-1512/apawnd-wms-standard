package com.maersk.apawnd.wms.standard.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReceiptMapper {

  int selectCountByWhIdAndShipmentNumberAndStatus(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber,
      @Param("status") String status);
}
