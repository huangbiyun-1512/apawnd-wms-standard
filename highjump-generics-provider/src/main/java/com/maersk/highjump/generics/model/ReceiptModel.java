package com.maersk.highjump.generics.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@Alias("ReceiptModel")
public class ReceiptModel implements Serializable {

  private String receiptId;
  private String vendorCode;
  private String poNumber;
  private Instant receiptDate;
  private String scacCode;
  private String status;
  private String itemNumber;
  private String lotNumber;
  private String lineNumber;
  private Integer scheduleNumber;
  private BigDecimal qtyReceived;
  private BigDecimal qtyDamaged;
  private String huId;
  private String packSlip;
  private String forkId;
  private String tranStatus;
  private String receiptUom;
  private String shipmentNumber;
  private String whId;
  private Integer storedAttributeId;
  private BigDecimal receivedCbm;
  private BigDecimal receivedWgt;
  private Instant endReceiptDate;
  private String customsUniqueNo;
  private String opentruck;
  private String remark;
  private Integer individual;
  private Long serialKey;
  private String truckNumber;
  private String invoiceNumber;
  private String scannedUpc;
  private Instant invoiceDate;
  private String customerCode;
  private Instant manufactureDate;
  private Instant expirationDate;
  private String grnSendSign;
  private Instant grnSendDate;
  private String damagedReasonCode;
  private BigDecimal length;
  private BigDecimal width;
  private BigDecimal height;
  private BigDecimal weight;
  private Long processAttributeId;
  private Instant updatedDate;

}
