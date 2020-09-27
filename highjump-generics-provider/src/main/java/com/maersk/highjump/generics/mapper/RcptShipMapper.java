package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.RcptShipModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RcptShipMapper {

  void insert(RcptShipModel rcptShipModel);

  int selectCountByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);

  List<RcptShipModel> selectByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);

  List<RcptShipModel> selectDetailByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);

  int deleteByWhIdAndShipmentNumber(
      @Param("whId") String whId,
      @Param("shipmentNumber") String shipmentNumber);
}
