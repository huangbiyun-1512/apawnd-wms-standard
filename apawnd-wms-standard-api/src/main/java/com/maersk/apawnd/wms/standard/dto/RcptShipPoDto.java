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

  @Schema(name = "rcpt_ship_po_detail_list", description = "PO detail list under current PO")
  @NotEmpty
  private List<RcptShipPoDetailDto> rcptShipPoDetailList;

  @Schema(name = "po_number", description = "Purchase order number")
  @NotEmpty
  @Size(max = 30)
  private String poNumber;

  @Schema(name = "action_code", description = "Action code(Create, Update, Delete, Merge)")
  private String actionCode;

  @Schema(name = "cases_expected", description = "Expected cases(default to 0)")
  private BigDecimal casesExpected;

  @Schema(name = "cases_received", description = "Received cases(default to 0)")
  private BigDecimal casesReceived;

  @Schema(name = "open_to_buy_date", description = "Date open to buy")
  private Instant openToBuyDate;
}
