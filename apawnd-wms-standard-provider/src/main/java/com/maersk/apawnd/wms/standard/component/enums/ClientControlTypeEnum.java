package com.maersk.apawnd.wms.standard.component.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ClientControlTypeEnum {
  CLIENT_CONTROL_TYPE_EDI_TRIGGER("EDITrigger");

  private String code;
}
