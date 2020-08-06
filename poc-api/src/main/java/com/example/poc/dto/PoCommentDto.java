package com.example.poc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class PoCommentDto {

  private String actionCode;
  private String lineNumber;
  private Integer scheduleNumber;
  private String commentType;
  private Instant commentDate;
  private String commentText;
  private Integer sequence;
}
