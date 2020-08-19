package com.example.poc.mapper;

import com.example.poc.model.RcptShipCartonDetailModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcptShipCartonDetailMapper {

  int insert(RcptShipCartonDetailModel rcptShipCartonDetailModel);
}
