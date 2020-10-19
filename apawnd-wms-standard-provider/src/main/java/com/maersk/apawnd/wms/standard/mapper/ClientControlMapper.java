package com.maersk.apawnd.wms.standard.mapper;

import com.maersk.apawnd.wms.standard.model.ClientControlModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClientControlMapper {

  List<ClientControlModel> selectByWhIdAndClientCodeAndControlTypeAndC1(
      @Param("whId") String whId,
      @Param("clientCode") String clientCode,
      @Param("controlType") String controlType,
      @Param("c1") String c1);
}
