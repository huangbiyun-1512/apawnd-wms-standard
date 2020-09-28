package com.maersk.asia.wms.standard.dc.mapper;

import com.maersk.asia.wms.standard.dc.model.RcptShipPoModel;
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
