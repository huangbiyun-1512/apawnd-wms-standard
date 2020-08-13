package com.example.poc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class AsnDto implements Serializable {

  private List<RcptShipPoDto> poList;
  private String whId;
  private String clientCode;
  private String shipmentNumber;
  private String carrierName;
  private String trailerNumber;
  private Instant dateExpected;
  private Instant dateReceived;
  private Instant dateShipped;
  private String status;
  private String comments;
  private Integer workersAssigned;
  private String proNumber;
  private String arrivalSign;
  private Instant arrivalDate;
  private String gmSendSign;
  private Instant gmSendDate;
  private Instant shipmentReceiptDate;
  private String asnType;
  private String wipNumber;
  private String fileSeq;
  private String tallysheetIsPrinted;
}
