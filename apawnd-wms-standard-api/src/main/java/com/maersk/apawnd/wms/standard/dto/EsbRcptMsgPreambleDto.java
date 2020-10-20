package com.maersk.apawnd.wms.standard.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EsbRcptMsgPreambleDto implements Serializable {

  private String hjsParentId;
  private String hjsNodeId;
  private Integer hjsSequence;
  private Integer hjsErrorNumber;
  private String hjsErrorMessage;
  private String msgPreamble;
  private String sender;
  private String receiver;
  private String messageType;
  private String messageVersion;
  private String messageDate;
  private String messageId;
}
