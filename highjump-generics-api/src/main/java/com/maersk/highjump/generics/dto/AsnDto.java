package com.maersk.highjump.generics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class AsnDto implements Serializable {

  @Schema(name = "po_list")
  private List<RcptShipPoDto> poList;
  @NotEmpty
  @Schema(name = "wh_id")
  private String whId;
  @Schema(name = "client_code")
  private String clientCode;
  @Schema(name = "shipment_number")
  private String shipmentNumber;
  @Schema(name = "carrier_name")
  private String carrierName;
  @Schema(name = "trailer_number")
  private String trailerNumber;
  @Schema(name = "date_expected")
  private Instant dateExpected;
  @Schema(name = "date_received")
  private Instant dateReceived;
  @Schema(name = "date_shipped")
  private Instant dateShipped;
  @Schema(name = "status")
  private String status;
  @Schema(name = "comments")
  private String comments;
  @Schema(name = "workers_assigned")
  private Integer workersAssigned;
  @Schema(name = "pro_number")
  private String proNumber;
  @Schema(name = "arrival_sign")
  private String arrivalSign;
  @Schema(name = "arrival_date")
  private Instant arrivalDate;
  @Schema(name = "grn_send_sign")
  private String grnSendSign;
  @Schema(name = "grn_send_date")
  private Instant grnSendDate;
  @Schema(name = "shipment_receipt_date")
  private Instant shipmentReceiptDate;
  @Schema(name = "asn_type")
  private String asnType;
  @Schema(name = "wip_number")
  private String wipNumber;
  @Schema(name = "file_seq")
  private String fileSeq;
  @Schema(name = "tallysheet_is_printed")
  private String tallysheetIsPrinted;
}
