package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.RcptShipModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RcptShipMapper {

  int insert(RcptShipModel rcptShipModel);
}
