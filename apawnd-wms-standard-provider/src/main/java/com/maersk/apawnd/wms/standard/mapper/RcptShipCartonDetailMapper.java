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
}
