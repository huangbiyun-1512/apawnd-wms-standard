package com.maersk.asia.wms.standard.mapper;

import com.maersk.asia.wms.standard.model.RcptShipPoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RcptShipPoMapper {

  void insert(RcptShipPoModel rcptShipPoModel);

  void bulkInsert(List<RcptShipPoModel> rcptShipPoModelList);

  int bulkUpdate(List<RcptShipPoModel> rcptShipPoModelList);

  int deleteByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);

}
