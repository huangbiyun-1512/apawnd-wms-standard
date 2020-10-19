package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.EsbRcptSerialNoModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EsbRcptSerialNoMapper {

  List<EsbRcptSerialNoModel> selectByHjsParentId(@Param("hjsParentId") String hjsParentId);
}
