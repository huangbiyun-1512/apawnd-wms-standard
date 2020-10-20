package com.maersk.apawnd.wms.standard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EsbRcptSerialNoDto implements Serializable {

  private String hjsParentId;
  private String hjsNodeId;
  private Integer hjsSequence;
  private Integer hjsErrorNumber;
  private Integer hjsErrorMessage;
  private String serialNo;
}
