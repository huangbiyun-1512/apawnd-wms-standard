package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.RcptShipPoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RcptShipPoMapper {

  void insert(RcptShipPoModel rcptShipPoModel);

  void bulkInsert(List<RcptShipPoModel> rcptShipPoModelList);

  int update(RcptShipPoModel rcptShipPoModel);

  int bulkUpdate(List<RcptShipPoModel> rcptShipPoModelList);

  int deleteByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);

  int deleteByWhIdAndShipmentNumberAndPoNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber,
      @Param("poNumber") String poNumber);

  int selectCountByWhIdAndShipmentNumberAndPoNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber,
      @Param("poNumber") String poNumber);

}
