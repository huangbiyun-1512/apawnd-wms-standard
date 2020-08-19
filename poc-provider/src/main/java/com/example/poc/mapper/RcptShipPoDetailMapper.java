package com.example.poc.mapper;

import com.example.poc.model.RcptShipPoDetailModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RcptShipPoDetailMapper {

  int insert(RcptShipPoDetailModel rcptShipPoDetailModel);
  int insertBatch(List<RcptShipPoDetailModel> rcptShipPoDetailModelList);
}
