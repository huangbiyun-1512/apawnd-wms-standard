package com.maersk.apawnd.wms.standard.component.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActionCodeEnum {
  ACTION_CODE_CREATE("Create"),
  ACTION_CODE_UPDATE("Update"),
  ACTION_CODE_DELETE("Delete"),
  ACTION_CODE_MERGE("Merge"),
  ACTION_CODE_EXIST_BY_PASS("ExistByPass"),
  ACTION_CODE_ADD_AFTER_DELETE("AddAfterDelete");

  private String code;
}
