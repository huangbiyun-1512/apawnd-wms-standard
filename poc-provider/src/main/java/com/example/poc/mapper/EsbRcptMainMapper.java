package com.example.poc.mapper;

import com.example.poc.model.EsbRcptMainModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EsbRcptMainMapper {

  int insert(EsbRcptMainModel esbRcptMainModel);
}
