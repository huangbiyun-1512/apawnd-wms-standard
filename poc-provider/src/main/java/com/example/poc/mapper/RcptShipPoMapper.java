package com.example.poc.mapper;

import com.example.poc.model.RcptShipPoModel;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface RcptShipPoMapper {

  int insert(RcptShipPoModel rcptShipPoModel);
  int insertBatch(List<RcptShipPoModel> rcptShipPoModelList);
}
