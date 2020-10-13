package com.maersk.apawnd.wms.standard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@Alias("EventQueueApiModel")
public class EventQueueApiModel implements Serializable {

  private String eventId;
  private Integer eventClass;
  private String eventType;
  private String eventName;
  private String eventData;
  private Instant dateAdded;
  private Instant dateStarted;
  private Instant dateFinished;
  private Integer retryCount;
  private Long fifoSequence;
  private Integer priority;
  private String status;
  private String errorMsg;
}
