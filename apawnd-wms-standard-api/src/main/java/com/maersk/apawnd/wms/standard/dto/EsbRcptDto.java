package com.maersk.apawnd.wms.standard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EsbRcptDto implements Serializable {

  @JsonProperty(value = "hjsNodeId")
  private String hjsNodeId;
  @JsonProperty(value = "hjsSequence")
  private Integer hjsSequence;
  @JsonProperty(value = "hjsErrorNumber")
  private Integer hjsErrorNumber;
  @JsonProperty(value = "hjsErrorMessage")
  private String hjsErrorMessage;
  @JsonProperty(value = "RCPT_MSGPREAMBLE_COLLECTION")
  private EsbRcptMsgPreambleCollectionDto esbRcptMsgPreambleCollectionDto;
  @JsonProperty(value = "RCPT_MAIN_COLLECTION")
  private EsbRcptMainCollectionDto esbRcptMainCollectionDto;
}
