package com.maersk.apawnd.wms.standard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class GrnAckDto implements Serializable {

  @JsonProperty(value = "RCPT")
  private EsbRcptDto esbRcptDto;
}
