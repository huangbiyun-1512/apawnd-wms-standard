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
public class RcptShipPoDetailDto implements Serializable {

  @Schema(name = "carton_list")
  private List<RcptShipPoDetailCartonDto> cartonList;
  @Schema(name = "action_code")
  private String actionCode;
  @Schema(name = "line_number")
  private String lineNumber;
  @Schema(name = "item_number")
  private String itemNumber;
  @Schema(name = "schedule_number")
  private Integer scheduleNumber;
  @Schema(name = "expected_qty")
  private BigDecimal expectedQty;
  @Schema(name = "received_qty")
  private BigDecimal ReceivedQty;
  @Schema(name = "reconciled_date")
  private Instant reconciledDate;
  @Schema(name = "status")
  private String status;
  @Schema(name = "shipment_line_number")
  private String shipmentLineNumber;
  @Schema(name = "weight")
  private BigDecimal weight;
  @Schema(name = "calculation_uom")
  private String calculationUom;
  @Schema(name = "property_mark")
  private String propertyMark;
  @Schema(name = "generic_field1")
  private String genericField1;
  @Schema(name = "generic_field2")
  private String genericField2;
  @Schema(name = "generic_field3")
  private String genericField3;
  @Schema(name = "generic_field4")
  private String genericField4;
  @Schema(name = "generic_field5")
  private String genericField5;
  @Schema(name = "generic_field6")
  private String genericField6;
  @Schema(name = "generic_field7")
  private String genericField7;
  @Schema(name = "generic_field8")
  private String genericField8;
  @Schema(name = "generic_field9")
  private String genericField9;
  @Schema(name = "generic_field10")
  private String genericField10;
  @Schema(name = "generic_field11")
  private String genericField11;
  @Schema(name = "generic_field12")
  private String genericField12;
  @Schema(name = "generic_field13")
  private String genericField13;
  @Schema(name = "generic_field14")
  private String genericField14;
  @Schema(name = "generic_field15")
  private String genericField15;
  @Schema(name = "grn_send_sign")
  private String grnSendSign;
  @Schema(name = "grn_send_date")
  private Instant grnSendDate;
  @Schema(name = "customs_is_approved")
  private String customsIsApproved;
  @Schema(name = "bonded_qty")
  private BigDecimal bondedQty;
  @Schema(name = "free_qty")
  private BigDecimal freeQty;
  @Schema(name = "confirm_bounded")
  private BigDecimal confirmBounded;
  @Schema(name = "confirm_free")
  private BigDecimal confirmFree;
  @Schema(name = "confirm_kit")
  private BigDecimal confirmKit;
  @Schema(name = "received_bonded_qty")
  private BigDecimal receivedBondedQty;
  @Schema(name = "received_free_qty")
  private BigDecimal receivedFreeQty;
}
