package com.example.poc.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class EsbRcptModel {

  private String hjsNodeId;
  private Integer hjsSequence;
  private Integer hjsErrorNumber;
  private String hjsErrorMessage;
}
