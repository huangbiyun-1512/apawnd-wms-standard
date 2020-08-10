package com.example.poc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
public class TPoDto implements Serializable {

  @Schema(name = "po_number", required = true)
  @NotEmpty
  private String poNumber;
  @Schema(name = "type_id")
  private String typeId;
  @Schema(name = "vendor_code")
  private String vendorCode;
  @Schema(name = "create_date")
  private Instant createDate;
  @Schema(name = "wh_id")
  private String whId;
  @Schema(name = "status")
  private String status;
  @Schema(name = "type")
  private String type;
  @Schema(name = "class_id")
  private String classId;
  @Schema(name = "remarks")
  private String remarks;
  @Schema(name = "order_pcs")
  private BigDecimal orderPcs;
  @Schema(name = "order_weight")
  private BigDecimal orderWeight;
  @Schema(name = "order_cbm")
  private BigDecimal orderCbm;
  @Schema(name = "carrier_code")
  private String carrierCode;
  @Schema(name = "owner_id")
  private String ownerId;
  @Schema(name = "client_order_number")
  private String clientOrderNumber;
  @Schema(name = "display_po_number")
  private String displayPoNumber;
  @Schema(name = "client_code")
  private String clientCode;
  @Schema(name = "residential_flg")
  private String residentialFlg;
  @Schema(name = "ship_from_name")
  private String shipFromName;
  @Schema(name = "ship_from_addr1")
  private String shipFromAddr1;
  @Schema(name = "ship_from_addr2")
  private String shipFromAddr2;
  @Schema(name = "ship_from_city")
  private String shipFromCity;
  @Schema(name = "ship_from_state")
  private String shipFromState;
  @Schema(name = "ship_from_postal_code")
  private String shipFromPostalCode;
  @Schema(name = "ship_from_country_code")
  private String shipFromCountryCode;
  @Schema(name = "ship_from_attention")
  private String shipFromAttention;
  @Schema(name = "ship_from_phone")
  private String shipFromPhone;
  @Schema(name = "ship_from_fax")
  private String shipFromFax;
  @Schema(name = "carrier_scac")
  private String carrierScac;
  @Schema(name = "carrier_mode")
  private String carrierMode;
  @Schema(name = "service_level")
  private String serviceLevel;
  @Schema(name = "freight_terms")
  private String FreightTerms;
  @Schema(name = "customer_code")
  private String customerCode;
  @Schema(name = "shipment_method")
  private String shipmentMethod;
  @Schema(name = "specialist")
  private String specialist;
  @Schema(name = "customer_name")
  private String customerName;
  @Schema(name = "customer_number")
  private String customerNumber;
  @Schema(name = "udf4")
  private String udf4;
  @Schema(name = "port_of_departure")
  private String portOfDeparture;
  @Schema(name = "port_of_arrival")
  private String portOfArrival;
  @Schema(name = "vessel")
  private String vessel;
  @Schema(name = "eta_date")
  private Instant etaDate;
  @Schema(name = "seal1")
  private String seal1;
  @Schema(name = "seal2")
  private String seal2;
  @Schema(name = "lp_sequence")
  private Integer lpSequence;
  @Schema(name = "po_arrival")
  private String poArrival;
  @Schema(name = "generic_field1")
  private String genericField1;
  @Schema(name = "generic_field2")
  private String genericField2;
  @Schema(name = "generic_field3")
  private String genericField3;
  @Schema(name = "generic_field4")
  private String genericField4;
  @Schema(name = "generic_field5")
  private String genericField5;
  @Schema(name = "generic_field6")
  private String genericField6;
  @Schema(name = "generic_field7")
  private String genericField7;
  @Schema(name = "generic_field8")
  private String genericField8;
  @Schema(name = "generic_field9")
  private String genericField9;
  @Schema(name = "generic_field10")
  private String genericField10;
  @Schema(name = "comment_list")
  private List<TPoCommentDto> commentList;
  @Schema(name = "detail_list")
  private List<TPoDetailDto> detailList;

}
