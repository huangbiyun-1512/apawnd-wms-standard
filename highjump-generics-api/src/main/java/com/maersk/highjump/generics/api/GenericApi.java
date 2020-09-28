package com.maersk.highjump.generics.api;

import com.maersk.highjump.generics.dto.AsnDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;

@FeignClient(name = "highjump-generics")
public interface GenericApi {

  @Operation(summary = "Retrieve the ASN info.", tags = {"Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK")
  })
  ResponseEntity retrieveAsn(
      @Parameter(required = true) String whId,
      @Parameter(required = true) String shipmentNumber);

  @Operation(summary = "Create a new ASN.", tags = {"Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created")
  })
  ResponseEntity createAsn(
      @Parameter(
          required = true,
          schema = @Schema(implementation = AsnDto.class)) AsnDto asnDto);

  @Operation(summary = "Update the ASN.", tags = {"Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK")
  })
  ResponseEntity updateAsn(
      @Parameter(
          required = true,
          schema = @Schema(implementation = AsnDto.class)) AsnDto asnDto);

  @Operation(summary = "Update the ASN if existed, otherwise create a new one.", tags = {"Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK")
  })
  ResponseEntity mergeAsn(
      @Parameter(
          required = true,
          schema = @Schema(implementation = AsnDto.class)) AsnDto asnDto);

  @Operation(summary = "Delete the existed ASN.", tags = {"Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "204", description = "No Content")
  })
  ResponseEntity deleteAsn(
      @Parameter(required = true) String whId,
      @Parameter(required = true) String shipmentNumber,
      @Parameter(required = true) String clientCode);

  @Operation(summary = "Create a new ASN after delete the old one.", tags = {"Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created")
  })
  ResponseEntity renewAsn(
      @Parameter(
          required = true,
          schema = @Schema(implementation = AsnDto.class)) AsnDto asnDto);

}
