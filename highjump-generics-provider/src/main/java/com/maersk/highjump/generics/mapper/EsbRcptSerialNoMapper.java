package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.EsbRcptSerialNoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EsbRcptSerialNoMapper {

  int insert(EsbRcptSerialNoModel esbRcptSerialNoModel);
}
