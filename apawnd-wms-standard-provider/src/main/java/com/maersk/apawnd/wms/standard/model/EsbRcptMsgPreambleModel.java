package com.maersk.apawnd.wms.standard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;

@Data
@NoArgsConstructor
@Alias("EsbRcptMsgPreambleModel")
public class EsbRcptMsgPreambleModel implements Serializable {

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
