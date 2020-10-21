package com.maersk.apawnd.wms.standard.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
public class EsbRcptMainCollectionDto implements Serializable {

  @JsonProperty(value = "RCPT_MAIN")
  private List<EsbRcptMainDto> esbRcptMainDtoList;
}
