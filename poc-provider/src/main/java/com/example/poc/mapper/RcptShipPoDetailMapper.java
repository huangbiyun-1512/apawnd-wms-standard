package com.example.poc.mapper;

import com.example.poc.model.RcptShipPoDetailModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcptShipPoDetailMapper {

  int insert(RcptShipPoDetailModel rcptShipPoDetailModel);
}
