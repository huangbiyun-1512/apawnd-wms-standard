package com.maersk.apawnd.wms.standard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EsbRcptMsgPreambleDto implements Serializable {

  @JsonProperty(value = "hjsParentId")
  private String hjsParentId;
  @JsonProperty(value = "hjsNodeId")
  private String hjsNodeId;
  @JsonProperty(value = "hjsSequence")
  private Integer hjsSequence;
  @JsonProperty(value = "hjsErrorNumber")
  private Integer hjsErrorNumber;
  @JsonProperty(value = "hjsErrorMessage")
  private String hjsErrorMessage;
  @JsonProperty(value = "msgpreamble")
  private String msgPreamble;
  @JsonProperty(value = "sender")
  private String sender;
  @JsonProperty(value = "receiver")
  private String receiver;
  @JsonProperty(value = "messageType")
  private String messageType;
  @JsonProperty(value = "messageVersion")
  private String messageVersion;
  @JsonProperty(value = "messageDate")
  private String messageDate;
  @JsonProperty(value = "messageId")
  private String messageId;
}
