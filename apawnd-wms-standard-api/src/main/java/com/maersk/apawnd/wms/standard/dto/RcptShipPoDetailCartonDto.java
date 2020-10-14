package com.maersk.apawnd.wms.standard.dto;

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

  @Schema(name = "expect_qty", description = "Expect quantity")
  private Integer expectQty;

  @Schema(name = "ucc", description = "UCC number")
  @NotEmpty
  @Size(max = 50)
  private String ucc;

  @Schema(name = "reference1", description = "Reference1")
  @Size(max = 50)
  private String reference1;

  @Schema(name = "reference2", description = "Reference2")
  @Size(max = 50)
  private String reference2;

  @Schema(name = "reference3", description = "Reference3")
  @Size(max = 50)
  private String reference3;

  @Schema(name = "reference4", description = "Reference4")
  @Size(max = 50)
  private String reference4;

  @Schema(name = "reference5", description = "Reference5")
  @Size(max = 50)
  private String reference5;

  @Schema(name = "status", description = "Status")
  private String status;

  @Schema(name = "fork_id", description = "Fork ID")
  @Size(max = 30)
  private String forkId;

  @Schema(name = "dimension", description = "Dimension")
  @Size(max = 30)
  private String dimension;

  @Schema(name = "hu_id", description = "Hu ID")
  @Size(max = 30)
  private String huId;

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

  @Schema(name = "send_to_local_db", description = "Send to local DB or not(not used)")
  private Integer sendToLocalDb;

  @Schema(name = "send_date", description = "Local DB send date")
  private Instant sendDate;

  @Schema(name = "container_label", description = "Container label after packing(not used)")
  @Size(max = 30)
  private String containerLabel;

  @Schema(name = "picking_list_id", description = "Picking list ID after packing(not used)")
  @Size(max = 30)
  private String pickingListId;

  @Schema(name = "scan_flag", description = "Scan flag after packing(not used)")
  private Integer scanFlag;

  @Schema(name = "qty_received", description = "Received quantity")
  private Integer qtyReceived;
}
