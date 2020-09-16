package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.RcptShipPoModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RcptShipPoMapper {

  int insert(RcptShipPoModel rcptShipPoModel);
  int insertBatch(List<RcptShipPoModel> rcptShipPoModelList);
}
