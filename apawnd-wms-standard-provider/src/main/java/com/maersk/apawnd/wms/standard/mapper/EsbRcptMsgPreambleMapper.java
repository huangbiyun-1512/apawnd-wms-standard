package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.EsbRcptMsgPreambleModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EsbRcptMsgPreambleMapper {

  List<EsbRcptMsgPreambleModel> selectByHjsParentId(@Param("hjsParentId") String hjsParentId);
}
