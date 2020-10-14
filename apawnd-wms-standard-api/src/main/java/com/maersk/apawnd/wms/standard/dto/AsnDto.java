package com.maersk.apawnd.wms.standard.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class AsnDto implements Serializable {

  @Schema(name = "rcpt_ship_po_list", description = "PO list under current ASN")
  @NotEmpty
  private List<RcptShipPoDto> rcptShipPoList;

  @Schema(name = "wh_id", description = "Warehouse ID")
  @NotEmpty
  @Size(max = 10)
  private String whId;

  @Schema(name = "client_code", description = "Client code")
  @NotEmpty
  @Size(max = 30)
  private String clientCode;

  @Schema(name = "shipment_number", description = "Shipment number")
  @NotEmpty
  @Size(max = 30)
  private String shipmentNumber;

  @Schema(name = "carrier_name", description = "Carrier name")
  @NotEmpty
  private String carrierName;

  @Schema(name = "trailer_number", description = "Trailer number")
  @Size(max = 20)
  private String trailerNumber;

  @Schema(name = "date_expected", description = "Expected date")
  private Instant dateExpected;

  @Schema(name = "date_received", description = "Received date")
  private Instant dateReceived;

  @Schema(name = "date_shipped", description = "Shipped date")
  private Instant dateShipped;

  @Schema(name = "status", description = "Status code of shipment(O-Open, C-Closed, R-Reconciled)")
  private String status;

  @Schema(name = "comments", description = "Comments")
  @Size(max = 200)
  private String comments;

  @Schema(name = "workers_assigned", description = "The number of workers assigned to receive this shipment(default to 0)")
  private Integer workersAssigned;

  @Schema(name = "pro_number", description = "Bill of lading pro number")
  @Size(max = 30)
  private String proNumber;

  @Schema(name = "arrival_sign", description = "Arrival sign(Y-Yes, N-No)")
  private String arrivalSign;

  @Schema(name = "arrival_date", description = "Arrival date")
  private Instant arrivalDate;

  @Schema(name = "grn_send_sign", description = "Grn send sign(Y-Yes, N-No)")
  private String grnSendSign;

  @Schema(name = "grn_send_date", description = "Grn send date")
  private Instant grnSendDate;

  @Schema(name = "shipment_receipt_date", description = "Shipment receipt date")
  private Instant shipmentReceiptDate;

  @Schema(name = "asn_type", description = "ASN type")
  @Size(max = 50)
  private String asnType;

  @Schema(name = "wip_number", description = "Wip number")
  @Size(max = 50)
  private String wipNumber;

  @Schema(name = "file_seq", description = "File sequence")
  @Size(max = 10)
  private String fileSeq;

  @Schema(name = "tallysheet_is_printed", description = "Tallysheet is printed or not(Y-Yes, N-No)")
  private String tallysheetIsPrinted;
}
