package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.RcptShipPoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RcptShipPoMapper {

  void insert(RcptShipPoModel rcptShipPoModel);

  void insertBatch(List<RcptShipPoModel> rcptShipPoModelList);

  int deleteByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);

}
