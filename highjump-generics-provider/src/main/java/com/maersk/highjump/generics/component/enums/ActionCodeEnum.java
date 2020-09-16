package com.maersk.highjump.generics.component.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ActionCodeEnum {

  ACTION_CODE_CREATE("0", "Create"),
  ACTION_CODE_UPDATE("1", "Update"),
  ACTION_CODE_MERGE("2", "Merge"),
  ACTION_CODE_EXIST_BY_PASS("3", "ExistByPass"),
  ACTION_CODE_DELETE("4", "Delete"),
  ACTION_CODE_ADD_AFTER_DELETE("4", "AddAfterDelete");

  private String code;
  private String name;

}
