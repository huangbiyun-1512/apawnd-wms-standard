package com.maersk.apawnd.wms.standard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@Alias("PoMasterModel")
public class PoMasterModel implements Serializable {

  private String poNumber;
  private Integer typeId;
  private String vendorCode;
  private String whId;
  private String status;
  private String type;
  private String classId;
  private String remarks;
  private BigDecimal orderPcs;
  private BigDecimal orderWeight;
  private BigDecimal orderCbm;
  private String carrierCode;
  private String ownerId;
  private String clientOrderNumber;
  private String displayPoNumber;
  private String clientCode;
  private String residentialFlag;
  private String shipFromName;
  private String shipFromAddr1;
  private String shipFromAddr2;
  private String shipFromCity;
  private String shipFromState;
  private String shipFromPostalCode;
  private String shipFromCountryCode;
  private String shipFromAttention;
  private String shipFromPhone;
  private String shipFromFax;
  private String carrierScac;
  private String carrierMode;
  private String serviceLevel;
  private String freightTerms;
  private String customerCode;
  private String shipmentMethod;
  private String specialist;
  private String customerName;
  private String customerNumber;
  private String udf4;
  private String portOfDeparture;
  private String portOfArrival;
  private String vessel;
  private Instant etaDate;
  private String seal1;
  private String seal2;
  private Integer lpSequence;
  private String poArrival;
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
  private String countryOfOrign;
  private Instant genericDate1;
  private Instant genericDate2;
  private Instant genericDate3;
  private BigDecimal genericQty1;
  private BigDecimal genericQty2;
  private BigDecimal genericQty3;
  private String genericField11;
  private String genericField12;
  private String genericField13;
  private String genericField14;
  private String genericField15;
  private String createdBy;
  private String updatedBy;
  private Instant createDate;
  private Instant updatedDate;
}
