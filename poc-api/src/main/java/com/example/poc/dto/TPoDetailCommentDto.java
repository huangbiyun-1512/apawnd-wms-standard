package com.example.poc.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class TPoDetailCommentDto implements Serializable {

  @Schema(name = "po_detail_comment_id")
  private Integer poDetailCommentId;
  @Schema(name = "wh_id")
  private String whId;
  @Schema(name = "po_number")
  private Integer poNumber;
  @Schema(name = "line_number")
  private String lineNumber;
  @Schema(name = "item_number")
  private String itemNumber;
  @Schema(name = "schedule_number")
  private Integer scheduleNumber;
  @Schema(name = "sequence")
  private Integer sequence;
  @Schema(name = "comment_type")
  private String commentType;
  @Schema(name = "comment_date")
  private Instant commentDate;
  @Schema(name = "comment_text")
  private String commentText;
  @Schema(name = "generic_field1")
  private String genericField1;
}
