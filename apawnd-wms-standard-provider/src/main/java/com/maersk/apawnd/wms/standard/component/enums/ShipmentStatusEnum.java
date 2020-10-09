package com.maersk.apawnd.wms.standard.component.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShipmentStatusEnum {
  SHIPMENT_STATUS_CODE_OPEN("O", "Open"),
  SHIPMENT_STATUS_CODE_RECONCILED("R", "Reconciled"),
  SHIPMENT_STATUS_CODE_CLOSED("C", "Closed");

  private String code;
  private String name;
}
