package com.example.poc.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class PoDetailCommentDto implements Serializable {

  private String actionCode;
  private Integer sequence;
  private String commentType;
  private Instant commentDate;
  private String commentText;
  private String genericField1;
}
