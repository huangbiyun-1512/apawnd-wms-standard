package com.maersk.apawnd.wms.standard.api;

import com.maersk.apawnd.wms.standard.dto.AsnDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;

@FeignClient(name = "dc-generics")
@Validated
public interface DcGenericApi {

  @Operation(summary = "Retrieve ASN.", tags = {"DC Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK")
  })
  ResponseEntity retrieveAsn(
      @Parameter @NotBlank String whId,
      @Parameter @NotBlank String shipmentNumber);

  @Operation(summary = "Create ASN.", tags = {"DC Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created")
  })
  ResponseEntity createAsn(
      @Parameter(schema = @Schema(implementation = AsnDto.class)) @Valid AsnDto asnDto);

  @Operation(summary = "Update ASN.", tags = {"DC Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK")
  })
  ResponseEntity updateAsn(
      @Parameter(schema = @Schema(implementation = AsnDto.class)) @Valid AsnDto asnDto);

  @Operation(summary = "Merge ASN. Update the ASN if existed, otherwise create a new one.", tags = {"DC Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK"),
      @ApiResponse(responseCode = "201", description = "Created")
  })
  ResponseEntity mergeAsn(
      @Parameter(schema = @Schema(implementation = AsnDto.class)) @Valid AsnDto asnDto);

  @Operation(summary = "Delete ASN.", tags = {"DC Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "200", description = "OK")
  })
  ResponseEntity deleteAsn(
      @Parameter @NotBlank String whId,
      @Parameter @NotBlank String shipmentNumber,
      @Parameter @NotBlank String clientCode);

  @Operation(summary = "Replace ASN. Create a new ASN after delete the old one.", tags = {"DC Generic APIs"})
  @ApiResponses(value = {
      @ApiResponse(responseCode = "201", description = "Created")
  })
  ResponseEntity replaceAsn(
      @Parameter(schema = @Schema(implementation = AsnDto.class)) @Valid AsnDto asnDto);

}
