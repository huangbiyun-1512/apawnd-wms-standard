package com.maersk.apawnd.wms.standard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.Instant;
import java.util.List;

@Data
@NoArgsConstructor
@Alias("RcptShipModel")
public class RcptShipModel implements Serializable {

  private String whId;
	private String shipmentNumber;
	private Integer carrierId;
	private String trailerNumber;
	private Instant dateExpected;
	private Instant dateReceived;
  private Instant dateShipped;
  private String status;
	private String comments;
  private Integer workersAssigned;
	private String proNumber;
  private String arrivalSign;
  private Instant arrivalDate;
  private String grnSendSign;
  private Instant grnSendDate;
  private Instant shipmentReceiptDate;
  private String asnType;
  private String wipNumber;
  private String fileSeq;
  private String tallysheetIsPrinted;
  private List<RcptShipPoModel> rcptShipPoList;

}
