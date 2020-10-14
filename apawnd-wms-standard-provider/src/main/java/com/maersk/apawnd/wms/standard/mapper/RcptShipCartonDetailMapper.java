package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.RcptShipCartonDetailModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RcptShipCartonDetailMapper {

  void insert(RcptShipCartonDetailModel rcptShipCartonDetailModel);

  void bulkInsert(List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList);

  int bulkUpdate(List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList);

  int deleteByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);

  int deleteByWhIdAndShipmentNumberAndPoNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber,
      @Param("poNumber") String poNumber);

  int deleteByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber,
      @Param("poNumber") String poNumber,
      @Param("lineNumber") String lineNumber,
      @Param("itemNumber") String itemNumber,
      @Param("scheduleNumber") Integer scheduleNumber);
}
