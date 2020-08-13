package com.example.poc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class RcptShipPoDto implements Serializable {

  private List<RcptShipPoDetailDto> detailList;
  private String actionCode;
  private String poNumber;
  private BigDecimal casesExpected;
  private BigDecimal casesReceived;
  private Instant openToBuyDate;
}
