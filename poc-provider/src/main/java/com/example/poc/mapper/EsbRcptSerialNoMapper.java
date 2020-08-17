package com.example.poc.mapper;

import com.example.poc.model.EsbRcptSerialNoModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EsbRcptSerialNoMapper {

  int insert(EsbRcptSerialNoModel esbRcptSerialNoModel);
}
