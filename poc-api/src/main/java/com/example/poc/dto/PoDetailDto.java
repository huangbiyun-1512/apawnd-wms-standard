package com.example.poc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class PoDetailDto implements Serializable {

  private List<PoDetailCommentDto> commentList;
  private String actionCode;
  private String lineNumber;
  private String itemNumber;
  private Integer scheduleNumber;
  private BigDecimal qty;
  private String vendorItemNumber;
  private Instant deliveryDate;
  private String originator;
  private String orderUom;
  private String specialProcessing;
  private String locationId;
  private String distroProcess;
  private Integer vasProfileId;
  private Integer storedAttributeId;
  private BigDecimal orderCbm;
  private BigDecimal orderWeight;
  private Integer pcs;
  private String marks;
  private String hsCode;
  private String packingType;
  private String color;
  private String size;
  private BigDecimal pieces;
  private String customerReferenceNo;
  private String customerArticleNumber;
  private String customerCaseNumber;
  private String newFlag;
  private String customsSheetNo;
  private Instant custsheetnoEnterDt;
  private Instant earliestDeliveryDate;
  private Instant latestDeliveryDate;
  private Instant latestShipDate;
  private BigDecimal costPrice;
  private String supplierSo;
  private String genericField1;
  private String genericField2;
  private String genericField3;
  private BigDecimal edcPrice;
  private String inspection;
  private String shipmentFlow;
  private String productType;
  private String filler;
  private String palletDimension;
  private String maxLoad;
  private String palletLoad;
  private String lotNumber;
  private String customsSheetCode;
  private String originCustomsCode;
  private String o5;
  private String p3;
  private String l5;
  private String o4;
  private String o2;
  private String l4;
  private String priceUsd;
  private String amountUsd;
  private String composition;
  private String tariffNumber;
  private String remark;
  private String genericField4;
  private String genericField5;
  private String genericField6;
  private String genericField7;
  private String genericField8;
  private String genericField9;
  private String genericField10;
  private BigDecimal confirmBonded;
  private BigDecimal confirmFree;
  private BigDecimal confirmKit;
  private BigDecimal bondedQty;
  private BigDecimal freeQty;
  private BigDecimal asnQty;
  private BigDecimal preBondedQty;
  private BigDecimal preFreeQty;
  private String isRekitItem;

}
