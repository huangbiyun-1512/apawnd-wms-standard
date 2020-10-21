package com.maersk.apawnd.wms.standard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EsbRcptMsgPreambleCollectionDto implements Serializable {

  @JsonProperty(value = "RCPT_MSGPREAMBLE")
  private EsbRcptMsgPreambleDto esbRcptMsgPreambleDto;
}
