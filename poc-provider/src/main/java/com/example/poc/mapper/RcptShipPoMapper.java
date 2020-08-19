package com.example.poc.mapper;

import com.example.poc.model.RcptShipPoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcptShipPoMapper {

  int insert(RcptShipPoModel rcptShipPoModel);
}
