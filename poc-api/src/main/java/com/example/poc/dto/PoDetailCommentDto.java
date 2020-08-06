package com.example.poc.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.Instant;

@Data
@AllArgsConstructor
public class PoDetailCommentDto {

  private String actionCode;
  private Integer sequence;
  private String commentType;
  private Instant commentDate;
  private String commentText;
  private String genericField1;
}
