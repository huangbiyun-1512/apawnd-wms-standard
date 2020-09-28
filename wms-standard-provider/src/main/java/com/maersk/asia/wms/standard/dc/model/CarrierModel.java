package com.maersk.asia.wms.standard.dc.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@Alias("CarrierModel")
public class CarrierModel implements Serializable {

  private Integer carrierId;
  private String carrierCode;
  private String carrierName;
  private String scacCode;
  private String transportMode;
  private Integer carrierGroupId;
  private String contactName;
  private String cdlVerify;
  private Integer timeAllowedEarly;
  private Integer timeAllowedLate;
  private String disposition;
  private Integer defaultTrailerTypeId;
  private String defaultPriority;
  private String dockScheduleMethod;
  private String notes;
  private Instant effective;
  private String status;
  private String freightFwdFlag;
  private String address1;
  private String address2;
  private String address3;
  private String address4;
  private String city;
  private String county;
  private String state;
  private String zip;
  private String country;
  private String countryCode;
  private String phone;
  private String extension;
  private String fax;
  private String email;
  private String website;
  private String manifestCarrierFlag;
  private String flagshipCarrierCode;
  private Integer tenderResponseTime;
  private String brokerFlag;

}
