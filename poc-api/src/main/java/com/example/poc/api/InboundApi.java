package com.example.poc.api;

import com.common.poc.components.dto.BaseResponseDto;
import com.example.poc.dto.PoDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.ResponseEntity;

public interface InboundApi {

  @Operation(summary = "Create purchase order.", tags = {"inbound"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK",
          content = {@Content(
              schema = @Schema(implementation = BaseResponseDto.class))}),
      @ApiResponse(responseCode = "400", description = "Bad Request",
          content = {@Content(
              schema = @Schema(implementation = BaseResponseDto.class))}),
      @ApiResponse(responseCode = "401", description = "Unauthorized",
          content = {@Content(
              schema = @Schema(implementation = BaseResponseDto.class))}),
      @ApiResponse(responseCode = "500", description = "Server Internal Error",
          content = @Content(
              schema = @Schema(implementation = BaseResponseDto.class)))
  })
  ResponseEntity createPurchaseOrder(
      @Parameter(
          description = "Purchase order info DTO.",
          required = true,
          schema = @Schema(implementation = PoDto.class)) PoDto poDto);

}
