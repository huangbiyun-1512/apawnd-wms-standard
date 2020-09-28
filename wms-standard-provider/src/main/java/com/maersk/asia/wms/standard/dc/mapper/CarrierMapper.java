package com.maersk.asia.wms.standard.dc.mapper;

import com.maersk.asia.wms.standard.dc.model.CarrierModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarrierMapper {

  List<CarrierModel> selectByCarrierName(@Param("carrierName") String carrierName);
}
