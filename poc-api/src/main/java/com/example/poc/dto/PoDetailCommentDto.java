package com.example.poc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@NoArgsConstructor
public class PoDetailCommentDto {

  private String actionCode;
  private Integer sequence;
  private String commentType;
  private Instant commentDate;
  private String commentText;
  private String genericField1;
}
