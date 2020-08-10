package com.example.poc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class TPoCommentDto implements Serializable {

  @Schema(name = "po_number")
  private String poNumber;
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
  @Schema(name = "wh_id")
  private String whId;
}
