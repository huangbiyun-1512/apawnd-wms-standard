package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.CarrierModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CarrierMapper {

  List<CarrierModel> selectByCarrierName(@Param("carrierName") String carrierName);
}
