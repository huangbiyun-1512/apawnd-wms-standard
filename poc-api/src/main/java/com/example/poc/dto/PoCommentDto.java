package com.example.poc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class PoCommentDto implements Serializable {

  @Schema(name = "action_code")
  private String actionCode;
  @Schema(name = "line_number")
  private String lineNumber;
  @Schema(name = "schedule_number")
  private Integer scheduleNumber;
  @Schema(name = "comment_type")
  private String commentType;
  @Schema(name = "comment_date")
  private Instant commentDate;
  @Schema(name = "comment_text")
  private String commentText;
  @Schema(name = "sequence")
  private Integer sequence;
}
