package com.maersk.apawnd.wms.standard.component.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventNameEnum {
  EVENT_NAME_SCA_RCPT("SCA_RCPT", "GRN"),
  EVENT_NAME_SCA_PIPA("SCA_PIPA", "PIPA"),
  EVENT_NAME_SCA_SHORTAGE("SCA_SHORTAGE", "SHORTAGE"),
  EVENT_NAME_SCA_LOADED("SCA_LOADED", "LOAD"),
  EVENT_NAME_SCA_INV("SCA_INV", "INV"),
  EVENT_NAME_SCA_INVADJ("SCA_INVADJ", "INVADJ"),
  EVENT_NAME_SCA_ACK("SCA_ACK", "ACK"),
  EVENT_NAME_EMAIL_TRIGGER("EmailTrigger", "");

  private String code;
  private String uri;

  public static EventNameEnum getByCode(String code){
    for (EventNameEnum value : EventNameEnum.values()) {
      if(value.getCode().equals(code)){
        return value;
      }
    }
    return null;
  }
}
