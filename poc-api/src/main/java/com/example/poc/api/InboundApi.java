package com.example.poc.api;

import com.common.poc.components.dto.BaseResponseDto;
import com.example.poc.dto.AsnDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "poc-domain")
public interface InboundApi {

  @Operation(summary = "Create a new ASN.", tags = {"inbound"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK",
          content = {@Content(
              schema = @Schema(implementation = BaseResponseDto.class))})
  })
  ResponseEntity createAsn(
      @Parameter(
          required = true,
          schema = @Schema(implementation = AsnDto.class)) AsnDto asnDto);

}
