package com.example.poc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class RcptShipPoDto implements Serializable {

  @Schema(name = "detail_list")
  private List<RcptShipPoDetailDto> detailList;
  @Schema(name = "action_code")
  private String actionCode;
  @Schema(name = "po_number")
  private String poNumber;
  @Schema(name = "cases_expected")
  private BigDecimal casesExpected;
  @Schema(name = "cases_received")
  private BigDecimal casesReceived;
  @Schema(name = "open_to_buy_date")
  private Instant openToBuyDate;
}
