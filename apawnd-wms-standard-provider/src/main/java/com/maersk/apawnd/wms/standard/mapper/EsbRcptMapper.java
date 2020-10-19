package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.EsbRcptModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EsbRcptMapper {

  List<EsbRcptModel> selectByHjsNodeId(@Param("hjsNodeId") String hjsNodeId);
}
