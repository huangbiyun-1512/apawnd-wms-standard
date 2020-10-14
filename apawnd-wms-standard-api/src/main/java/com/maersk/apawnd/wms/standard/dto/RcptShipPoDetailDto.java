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
public class RcptShipPoDetailDto implements Serializable {

  @Schema(name = "rcpt_ship_po_detail_carton_list", description = "Carton list under current PO detail line")
  private List<RcptShipPoDetailCartonDto> rcptShipPoDetailCartonList;

  @Schema(name = "action_code", description = "Action code(Create, Update, Delete, Merge)")
  private String actionCode;

  @Schema(name = "line_number", description = "Line number")
  @NotEmpty
  @Size(max = 5)
  private String lineNumber;

  @Schema(name = "item_number", description = "Item number")
  @NotEmpty
  @Size(max = 30)
  private String itemNumber;

  @Schema(name = "schedule_number", description = "Schedule number")
  private Integer scheduleNumber;

  @Schema(name = "expected_qty", description = "Expected quantity")
  private BigDecimal expectedQty;

  @Schema(name = "received_qty", description = "Received quantity set during the shipment reconciliation")
  private BigDecimal ReceivedQty;

  @Schema(name = "reconciled_date", description = "Reconciled date")
  private Instant reconciledDate;

  @Schema(name = "status", description = "Status(O-Open, H-Host[sent to host], R-Reconciled)")
  private String status;

  @Schema(name = "shipment_line_number", description = "Shipment line number")
  @Size(max = 5)
  private String shipmentLineNumber;

  @Schema(name = "weight", description = "Weight")
  private BigDecimal weight;

  @Schema(name = "calculation_uom", description = "Calculation UOM")
  @Size(max = 10)
  private String calculationUom;

  @Schema(name = "property_mark", description = "Property mark")
  @Size(max = 50)
  private String propertyMark;

  @Schema(name = "generic_field1", description = "Generic field 1")
  @Size(max = 50)
  private String genericField1;

  @Schema(name = "generic_field2", description = "Generic field 2")
  @Size(max = 50)
  private String genericField2;

  @Schema(name = "generic_field3", description = "Generic field 3")
  @Size(max = 50)
  private String genericField3;

  @Schema(name = "generic_field4", description = "Generic field 4")
  @Size(max = 50)
  private String genericField4;

  @Schema(name = "generic_field5", description = "Generic field 5")
  @Size(max = 50)
  private String genericField5;

  @Schema(name = "generic_field6", description = "Generic field 6")
  @Size(max = 50)
  private String genericField6;

  @Schema(name = "generic_field7", description = "Generic field 7")
  @Size(max = 50)
  private String genericField7;

  @Schema(name = "generic_field8", description = "Generic field 8")
  @Size(max = 50)
  private String genericField8;

  @Schema(name = "generic_field9", description = "Generic field 9")
  @Size(max = 50)
  private String genericField9;

  @Schema(name = "generic_field10", description = "Generic field 10")
  @Size(max = 50)
  private String genericField10;

  @Schema(name = "generic_field11", description = "Generic field 11")
  @Size(max = 250)
  private String genericField11;

  @Schema(name = "generic_field12", description = "Generic field 12")
  @Size(max = 250)
  private String genericField12;

  @Schema(name = "generic_field13", description = "Generic field 13")
  @Size(max = 250)
  private String genericField13;

  @Schema(name = "generic_field14", description = "Generic field 14")
  @Size(max = 250)
  private String genericField14;

  @Schema(name = "generic_field15", description = "Generic field 15")
  @Size(max = 250)
  private String genericField15;

  @Schema(name = "grn_send_sign", description = "GRN send sign(Y-Yes, N-No)")
  private String grnSendSign;

  @Schema(name = "grn_send_date", description = "GRN send date")
  private Instant grnSendDate;

  @Schema(name = "customs_is_approved", description = "Customs is approved or not(Y-Yes, N-No)")
  @Size(max = 30)
  private String customsIsApproved;

  @Schema(name = "bonded_qty", description = "Bonded quantity")
  private BigDecimal bondedQty;

  @Schema(name = "free_qty", description = "Free quantity")
  private BigDecimal freeQty;

  @Schema(name = "confirm_bounded", description = "Confirm bounded")
  private BigDecimal confirmBounded;

  @Schema(name = "confirm_free", description = "Confirm free")
  private BigDecimal confirmFree;

  @Schema(name = "confirm_kit", description = "Confirm kit")
  private BigDecimal confirmKit;

  @Schema(name = "received_bonded_qty", description = "Received bonded quantity")
  private BigDecimal receivedBondedQty;

  @Schema(name = "received_free_qty", description = "Received free quantity")
  private BigDecimal receivedFreeQty;
}
