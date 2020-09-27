package com.maersk.highjump.generics.controller.v1;

import com.maersk.commons.component.annotation.AutoLogging;
import com.maersk.commons.component.dto.BaseResponseDto;
import com.maersk.highjump.generics.api.GenericApi;
import com.maersk.highjump.generics.dto.AsnDto;
import com.maersk.highjump.generics.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/generic")
public class GenericApiController implements GenericApi {

  private final AsnService asnService;

  public GenericApiController(AsnService asnService) {
    this.asnService = asnService;
  }

  @Override
  @GetMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity retrieveAsn(
      @RequestParam(name = "whId") String whId,
      @RequestParam(name = "shipmentNumber") String shipmentNumber) {
    AsnDto asnDto = asnService.retrieve(whId, shipmentNumber);

    return ResponseEntity.ok(BaseResponseDto.ok(asnDto));
  }

  @Override
  @PostMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity createAsn(@Valid @RequestBody AsnDto asnDto) {
    asnService.create(asnDto);

    return ResponseEntity
        .created(null)
        .body(BaseResponseDto.ok());
  }

  @Override
  @PutMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity replaceAsn(@Valid @RequestBody AsnDto asnDto) {
    asnService.replace(asnDto);

    return ResponseEntity
        .created(null)
        .body(BaseResponseDto.ok());
  }

  @Override
  @DeleteMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity deleteAsn(
      @RequestParam(name = "whId") String whId,
      @RequestParam(name = "shipmentNumber") String shipmentNumber,
      @RequestParam(name = "clientCode") String clientCode) {
    asnService.deleteByWhIdAndShipmentNumberAndClientCode(whId, shipmentNumber, clientCode);

    return ResponseEntity.noContent().build();
  }
}
