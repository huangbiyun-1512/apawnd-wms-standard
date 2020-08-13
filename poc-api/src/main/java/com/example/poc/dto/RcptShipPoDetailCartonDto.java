package com.example.poc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class RcptShipPoDetailCartonDto implements Serializable {

  private Integer ExpectQty;
  private String ucc;
  private String reference1;
  private String reference2;
  private String reference3;
  private String reference4;
  private String reference5;
  private String status;
  private String forkId;
  private String dimension;
  private String huId;
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
  private Integer sendToLocalDb;
  private Instant sendDate;
  private String containerLabel;
  private String pickingListId;
  private Integer scanFlag;
  private Integer qtyReceived;
}
