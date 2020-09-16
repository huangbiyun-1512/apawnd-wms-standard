package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.EsbRcptModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EsbRcptMapper {

  int insert(EsbRcptModel esbRcptModel);
}
