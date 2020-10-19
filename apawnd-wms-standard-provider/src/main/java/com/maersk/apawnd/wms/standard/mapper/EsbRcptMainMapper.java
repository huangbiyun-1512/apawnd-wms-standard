package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.EsbRcptMainModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface EsbRcptMainMapper {

  List<EsbRcptMainModel> selectByHjsParentId(@Param("hjsParentId") String hjsParentId);
}
