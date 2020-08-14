package com.example.poc.mapper;

import com.example.poc.model.EsbRcptModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EsbRcptMapper {

  int insert(EsbRcptModel esbRcptModel);
}
