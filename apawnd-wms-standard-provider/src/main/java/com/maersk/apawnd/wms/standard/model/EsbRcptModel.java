package com.maersk.apawnd.wms.standard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Alias("EsbRcptModel")
public class EsbRcptModel implements Serializable {

  private String hjsNodeId;
  private Integer hjsSequence;
  private Integer hjsErrorNumber;
  private String hjsErrorMessage;
}
