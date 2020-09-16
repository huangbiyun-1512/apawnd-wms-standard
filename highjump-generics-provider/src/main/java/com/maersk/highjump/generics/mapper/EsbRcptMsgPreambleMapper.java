package com.maersk.highjump.generics.mapper;

import com.maersk.highjump.generics.model.EsbRcptMsgPreambleModel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface EsbRcptMsgPreambleMapper {

  int insert(EsbRcptMsgPreambleModel esbRcptMsgPreambleModel);
}
