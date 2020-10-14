package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.RcptShipPoDetailModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RcptShipPoDetailMapper {

  void insert(RcptShipPoDetailModel rcptShipPoDetailModel);

  void bulkInsert(List<RcptShipPoDetailModel> rcptShipPoDetailModelList);

  int update(RcptShipPoDetailModel rcptShipPoDetailModel);

  int bulkUpdate(List<RcptShipPoDetailModel> rcptShipPoDetailModelList);

  int deleteByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);

  int deleteByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber,
      @Param("poNumber") String poNumber,
      @Param("lineNumber") String lineNumber,
      @Param("itemNumber") String itemNumber);

  int selectCountByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber,
      @Param("poNumber") String poNumber,
      @Param("lineNumber") String lineNumber,
      @Param("itemNumber") String itemNumber);
}
