package com.maersk.asia.wms.standard.mapper;

import com.maersk.asia.wms.standard.model.CarrierModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarrierMapper {

  List<CarrierModel> selectByCarrierName(@Param("carrierName") String carrierName);
}
