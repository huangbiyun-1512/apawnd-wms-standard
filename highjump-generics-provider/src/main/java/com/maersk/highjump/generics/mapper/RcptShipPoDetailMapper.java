package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.RcptShipPoDetailModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RcptShipPoDetailMapper {

  void insert(RcptShipPoDetailModel rcptShipPoDetailModel);

  void bulkInsert(List<RcptShipPoDetailModel> rcptShipPoDetailModelList);

  int bulkUpdate(List<RcptShipPoDetailModel> rcptShipPoDetailModelList);

  int deleteByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);
}
