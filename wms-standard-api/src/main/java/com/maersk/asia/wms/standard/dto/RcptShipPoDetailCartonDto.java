package com.maersk.asia.wms.standard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class RcptShipPoDetailCartonDto implements Serializable {

  @Schema(name = "expect_qty")
  private Integer expectQty;

  @Schema(name = "ucc")
  @NotEmpty
  @Size(max = 50)
  private String ucc;

  @Schema(name = "reference1")
  @Size(max = 50)
  private String reference1;

  @Schema(name = "reference2")
  @Size(max = 50)
  private String reference2;

  @Schema(name = "reference3")
  @Size(max = 50)
  private String reference3;

  @Schema(name = "reference4")
  @Size(max = 50)
  private String reference4;

  @Schema(name = "reference5")
  @Size(max = 50)
  private String reference5;

  @Schema(name = "status")
  private String status;

  @Schema(name = "fork_id")
  @Size(max = 30)
  private String forkId;

  @Schema(name = "dimension")
  @Size(max = 30)
  private String dimension;

  @Schema(name = "hu_id")
  @Size(max = 30)
  private String huId;

  @Schema(name = "generic_field1")
  @Size(max = 50)
  private String genericField1;

  @Schema(name = "generic_field2")
  @Size(max = 50)
  private String genericField2;

  @Schema(name = "generic_field3")
  @Size(max = 50)
  private String genericField3;

  @Schema(name = "generic_field4")
  @Size(max = 50)
  private String genericField4;

  @Schema(name = "generic_field5")
  @Size(max = 50)
  private String genericField5;

  @Schema(name = "generic_field6")
  @Size(max = 50)
  private String genericField6;

  @Schema(name = "generic_field7")
  @Size(max = 50)
  private String genericField7;

  @Schema(name = "generic_field8")
  @Size(max = 50)
  private String genericField8;

  @Schema(name = "generic_field9")
  @Size(max = 50)
  private String genericField9;

  @Schema(name = "generic_field10")
  @Size(max = 50)
  private String genericField10;

  @Schema(name = "generic_field11")
  @Size(max = 250)
  private String genericField11;

  @Schema(name = "generic_field12")
  @Size(max = 250)
  private String genericField12;

  @Schema(name = "generic_field13")
  @Size(max = 250)
  private String genericField13;

  @Schema(name = "generic_field14")
  @Size(max = 250)
  private String genericField14;

  @Schema(name = "generic_field15")
  @Size(max = 250)
  private String genericField15;

  @Schema(name = "send_to_local_db")
  private Integer sendToLocalDb;

  @Schema(name = "send_date")
  private Instant sendDate;

  @Schema(name = "container_label")
  @Size(max = 30)
  private String containerLabel;

  @Schema(name = "picking_list_id")
  @Size(max = 30)
  private String pickingListId;

  @Schema(name = "scan_flag")
  private Integer scanFlag;

  @Schema(name = "qty_received")
  private Integer qtyReceived;
}
