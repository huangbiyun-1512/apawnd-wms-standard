package com.maersk.apawnd.wms.standard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Alias("EsbRcptSerialNoModel")
public class EsbRcptSerialNo implements Serializable {

  private String hjsParentId;
  private String hjsNodeId;
  private Integer hjsSequence;
  private Integer hjsErrorNumber;
  private Integer hjsErrorMessage;
  private String serialNo;
}
