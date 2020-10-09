package com.maersk.apawnd.wms.standard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class RcptShipPoDto implements Serializable {

  @Schema(name = "rcpt_ship_po_detail_list")
  @NotEmpty
  private List<RcptShipPoDetailDto> rcptShipPoDetailList;

  @Schema(name = "po_number")
  @NotEmpty
  @Size(max = 30)
  private String poNumber;

  @Schema(name = "cases_expected")
  private BigDecimal casesExpected;

  @Schema(name = "cases_received")
  private BigDecimal casesReceived;

  @Schema(name = "open_to_buy_date")
  private Instant openToBuyDate;
}
