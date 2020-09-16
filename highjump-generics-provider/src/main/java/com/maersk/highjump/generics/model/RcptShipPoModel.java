package com.maersk.highjump.generics.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
public class RcptShipPoModel {

  private String whId;
	private String shipmentNumber;
	private String poNumber;
	private BigDecimal casesExpected;
  private BigDecimal casesReceived;
	private Instant openToBuyDate;

}
