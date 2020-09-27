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
@Alias("RcptShipPoModel")
public class RcptShipPoModel implements Serializable {

  private String whId;
	private String shipmentNumber;
	private String poNumber;
	private BigDecimal casesExpected;
  private BigDecimal casesReceived;
	private Instant openToBuyDate;
	private List<RcptShipPoDetailModel> rcptShipPoDetailList;
}
