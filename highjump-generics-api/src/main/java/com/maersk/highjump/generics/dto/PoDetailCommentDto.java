package com.maersk.highjump.generics.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.Instant;

@Data
@NoArgsConstructor
public class PoDetailCommentDto implements Serializable {

  @Schema(name = "action_code")
  private String actionCode;
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
