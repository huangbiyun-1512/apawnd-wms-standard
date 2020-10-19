package com.maersk.apawnd.wms.standard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@Alias("ClientControlModel")
public class ClientControlModel implements Serializable {

  private Integer clientControlId;
  private String whId;
  private String clientCode;
  private String controlType;
  private String description;
  private Integer nextValue;
  private String allowEdit;
  private String c1;
  private String c2;
  private BigDecimal f1;
  private String c3;
  private String c4;
}
