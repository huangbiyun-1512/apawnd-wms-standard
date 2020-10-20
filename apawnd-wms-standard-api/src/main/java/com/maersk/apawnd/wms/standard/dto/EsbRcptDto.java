package com.maersk.apawnd.wms.standard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class EsbRcptDto implements Serializable {

  private String hjsNodeId;
  private Integer hjsSequence;
  private Integer hjsErrorNumber;
  private String hjsErrorMessage;
  private List<EsbRcptMsgPreambleDto> esbRcptMsgPreambleDtoList;
  private List<EsbRcptMainDto> esbRcptMainDtoList;
}
