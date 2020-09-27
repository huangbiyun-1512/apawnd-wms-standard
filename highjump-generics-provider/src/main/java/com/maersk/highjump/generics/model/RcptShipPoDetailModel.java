package com.maersk.highjump.generics.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Alias("RcptShipPoDetailModel")
public class RcptShipPoDetailModel implements Serializable {

  private String whId;
	private String shipmentNumber;
  private String poNumber;
  private String lineNumber;
  private String itemNumber;
  private Integer scheduleNumber;
	private BigDecimal expectedQty;
  private BigDecimal receivedQty;
	private Instant reconciledDate;
  private String status;
  private String shipmentLineNumber;
  private BigDecimal weight;
  private String calculationUom;
  private String propertyMark;
  private String genericField1;
  private String genericField2;
  private String genericField3;
  private String genericField4;
  private String genericField5;
  private String genericField6;
  private String genericField7;
  private String genericField8;
  private String genericField9;
  private String genericField10;
  private String genericField11;
  private String genericField12;
  private String genericField13;
  private String genericField14;
  private String genericField15;
  private String grnSendSign;
  private Instant grnSendDate;
  private String customsIsApproved;
  private BigDecimal bondedQty;
  private BigDecimal freeQty;
  private BigDecimal confirmBounded;
  private BigDecimal confirmFree;
  private BigDecimal confirmKit;
  private BigDecimal receivedBondedQty;
  private BigDecimal receivedFreeQty;
  private List<RcptShipCartonDetailModel> rcptShipCartonDetailList;
}
