package com.maersk.apawnd.wms.standard.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.ibatis.type.Alias;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
@Alias("ApiEventMonitorModel")
public class ApiEventMonitorModel implements Serializable {

  private Integer monitorId;
  private String whId;
  private String clientCode;
  private String eventName;
  private String currentStatus;
  private String setStatus;
  private String processId;
  private Instant nextRunTime;
  private Instant lastRunTime;
  private String lastRunResult;
  private String applicationFolder;
  private Integer priority;
}
