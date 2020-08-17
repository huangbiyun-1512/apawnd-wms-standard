package com.example.poc.mapper;

import com.example.poc.model.EsbRcptMsgPreambleModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EsbRcptMsgPreambleMapper {

  int insert(EsbRcptMsgPreambleModel esbRcptMsgPreambleModel);
}
