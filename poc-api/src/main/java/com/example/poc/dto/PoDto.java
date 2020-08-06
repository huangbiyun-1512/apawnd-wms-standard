package com.example.poc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class PoDto implements Serializable {

  @NotEmpty
  private String poNumber;
  private String typeName;
  private String vendorCode;
  private Instant createDate;
  private String warehouseId;
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
  private String residentialFlg;
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
  private String FreightTerms;
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
  private List<PoCommentDto> commentList;
  private List<PoDetailDto> detailList;

}
