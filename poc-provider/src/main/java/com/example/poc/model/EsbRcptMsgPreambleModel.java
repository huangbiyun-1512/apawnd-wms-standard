package com.example.poc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EsbRcptMsgPreambleModel {

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
