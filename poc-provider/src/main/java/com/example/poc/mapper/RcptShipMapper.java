package com.example.poc.mapper;

import com.example.poc.model.RcptShipModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcptShipMapper {

  int insert(RcptShipModel rcptShipModel);
}
