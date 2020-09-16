package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.EsbRcptMainModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EsbRcptMainMapper {

  int insert(EsbRcptMainModel esbRcptMainModel);
}
