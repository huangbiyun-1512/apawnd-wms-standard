package com.example.poc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class RcptShipPoDetailCartonDto implements Serializable {

  @Schema(name = "expect_qty")
  private Integer ExpectQty;
  @Schema(name = "ucc")
  private String ucc;
  @Schema(name = "reference1")
  private String reference1;
  @Schema(name = "reference2")
  private String reference2;
  @Schema(name = "reference3")
  private String reference3;
  @Schema(name = "reference4")
  private String reference4;
  @Schema(name = "reference5")
  private String reference5;
  @Schema(name = "status")
  private String status;
  @Schema(name = "fork_id")
  private String forkId;
  @Schema(name = "dimension")
  private String dimension;
  @Schema(name = "hu_id")
  private String huId;
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
  @Schema(name = "send_to_local_db")
  private Integer sendToLocalDb;
  @Schema(name = "send_date")
  private Instant sendDate;
  @Schema(name = "container_label")
  private String containerLabel;
  @Schema(name = "picking_list_id")
  private String pickingListId;
  @Schema(name = "scan_flag")
  private Integer scanFlag;
  @Schema(name = "qty_received")
  private Integer qtyReceived;
}
