package com.maersk.apawnd.wms.standard.controller.v1;

import com.maersk.apawnd.commons.component.annotation.AutoLogging;
import com.maersk.apawnd.commons.component.dto.BaseResponseDto;
import com.maersk.apawnd.wms.standard.api.DcGenericApi;
import com.maersk.apawnd.wms.standard.dto.AsnDto;
import com.maersk.apawnd.wms.standard.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/dc")
public class DcGenericApiController implements DcGenericApi {

  private final AsnService asnService;

  public DcGenericApiController(AsnService asnService) {
    this.asnService = asnService;
  }

  @Override
  @GetMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity<BaseResponseDto<AsnDto>> retrieveAsn(
      @RequestParam(name = "wh_id") String whId,
      @RequestParam(name = "shipment_number") String shipmentNumber) {
    AsnDto asnDto = asnService.retrieve(whId, shipmentNumber);
    return ResponseEntity.ok(BaseResponseDto.ok(asnDto));
  }

  @Override
  @PostMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity<BaseResponseDto> createAsn(@Valid @RequestBody AsnDto asnDto) {
    asnService.create(asnDto);
    return ResponseEntity
        .created(null)
        .body(BaseResponseDto.ok());
  }

  @Override
  @PatchMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity updateAsn(@Valid @RequestBody AsnDto asnDto) {
    asnService.update(asnDto);
    return ResponseEntity.ok(BaseResponseDto.ok());
  }

  @Override
  @PutMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity<BaseResponseDto> mergeAsn(@Valid @RequestBody AsnDto asnDto) {
    if (asnService.merge(asnDto)) {
      return ResponseEntity.ok(BaseResponseDto.ok());
    }
    return ResponseEntity
        .created(null)
        .body(BaseResponseDto.ok());
  }

  @Override
  @DeleteMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity<BaseResponseDto> deleteAsn(
      @RequestParam(name = "wh_id") String whId,
      @RequestParam(name = "shipment_number") String shipmentNumber,
      @RequestParam(name = "client_code") String clientCode) {
    asnService.delete(whId, shipmentNumber, clientCode);
    return ResponseEntity.ok(BaseResponseDto.ok());
  }

  @Override
  @PutMapping(value = "/asn/replace", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity<BaseResponseDto> replaceAsn(@Valid @RequestBody AsnDto asnDto) {
    asnService.replace(asnDto);
    return ResponseEntity
        .created(null)
        .body(BaseResponseDto.ok());
  }
}
