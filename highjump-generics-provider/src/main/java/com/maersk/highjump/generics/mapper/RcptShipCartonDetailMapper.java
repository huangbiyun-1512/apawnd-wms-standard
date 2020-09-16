package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.RcptShipCartonDetailModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RcptShipCartonDetailMapper {

  int insert(RcptShipCartonDetailModel rcptShipCartonDetailModel);
  int insertBatch(List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList);
}
