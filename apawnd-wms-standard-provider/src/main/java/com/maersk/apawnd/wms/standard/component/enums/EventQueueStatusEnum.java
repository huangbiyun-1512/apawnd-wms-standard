package com.maersk.apawnd.wms.standard.component.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum EventQueueStatusEnum {

  EVENT_QUEUE_STATUS_NEW("NEW"),
  EVENT_QUEUE_STATUS_START("START"),
  EVENT_QUEUE_STATUS_ERROR("ERROR"),
  EVENT_QUEUE_STATUS_MAIL("MAIL"),
  EVENT_QUEUE_STATUS_DONE("DONE");

  private String code;
}
