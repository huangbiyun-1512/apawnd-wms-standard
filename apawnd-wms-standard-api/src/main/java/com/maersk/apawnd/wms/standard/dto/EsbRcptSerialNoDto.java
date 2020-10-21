package com.maersk.apawnd.wms.standard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class EsbRcptSerialNoDto implements Serializable {

  @JsonProperty(value = "hjsParentId")
  private String hjsParentId;
  @JsonProperty(value = "hjsNodeId")
  private String hjsNodeId;
  @JsonProperty(value = "hjsSequence")
  private Integer hjsSequence;
  @JsonProperty(value = "hjsErrorNumber")
  private Integer hjsErrorNumber;
  @JsonProperty(value = "hjsErrorMessage")
  private Integer hjsErrorMessage;
  @JsonProperty(value = "serialNo")
  private String serialNo;
}
