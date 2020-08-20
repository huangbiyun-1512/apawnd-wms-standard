package com.example.poc.mapper;

import com.example.poc.model.RcptShipCartonDetailModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RcptShipCartonDetailMapper {

  int insert(RcptShipCartonDetailModel rcptShipCartonDetailModel);
  int insertBatch(List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList);
}
