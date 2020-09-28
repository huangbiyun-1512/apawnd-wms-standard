package com.maersk.asia.wms.standard.component.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ReceiptStatusEnum {
  RECEIPT_STATUS_CODE_OPEN("O", "Open");

  private String code;
  private String name;
}
