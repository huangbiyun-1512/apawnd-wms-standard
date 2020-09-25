package com.maersk.highjump.generics.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ReceiptMapper {

  int selectCountByWhIdAndShipmentNumberAndStatus(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber,
      @Param("status") String status);
}
