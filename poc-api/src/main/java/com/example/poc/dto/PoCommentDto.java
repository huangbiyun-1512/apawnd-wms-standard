package com.example.poc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class PoCommentDto implements Serializable {

  private String actionCode;
  private String lineNumber;
  private Integer scheduleNumber;
  private String commentType;
  private Instant commentDate;
  private String commentText;
  private Integer sequence;
}
