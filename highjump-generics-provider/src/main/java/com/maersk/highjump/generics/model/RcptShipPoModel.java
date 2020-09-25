package com.maersk.highjump.generics.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.math.BigDecimal;
import java.time.Instant;

@Data
@NoArgsConstructor
@Alias("RcptShipPoModel")
public class RcptShipPoModel {

  private String whId;
	private String shipmentNumber;
	private String poNumber;
	private BigDecimal casesExpected;
  private BigDecimal casesReceived;
	private Instant openToBuyDate;

}
