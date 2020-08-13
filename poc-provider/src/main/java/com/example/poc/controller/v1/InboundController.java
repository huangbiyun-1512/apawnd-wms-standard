package com.example.poc.controller.v1;

import com.common.poc.components.annotation.AutoLogging;
import com.common.poc.components.dto.BaseResponseDto;
import com.example.poc.api.InboundApi;
import com.example.poc.dto.AsnDto;
import com.example.poc.dto.PoDto;
import com.example.poc.model.EsbRcptModel;
import com.example.poc.service.AsnService;
import com.github.pagehelper.Page;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/inbound")
public class InboundController implements InboundApi {

  private final AsnService asnService;

  public InboundController(AsnService asnService) {
    this.asnService = asnService;
  }

  @Override
  @PostMapping(value = "/po", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity createPurchaseOrder(@Valid @RequestBody PoDto poDto) {
    return ResponseEntity
        .created(null)
        .body(BaseResponseDto.ok());
  }

  @Override
  @GetMapping(value = "/po", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity queryPurchaseOrder(
      @RequestParam("po_number") String poNumber,
      @RequestParam("wh_id") String whId) {
    return ResponseEntity.ok().body(BaseResponseDto.ok(env));
  }

  @Override
  @PostMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity createAsn(@Valid @RequestBody AsnDto asnDto) {
    asnService.createAsn(asnDto);
    return ResponseEntity
        .created(null)
        .body(BaseResponseDto.ok());
  }

  @Override
  @GetMapping(value = "/asn", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity findAsnByPage(
      @RequestParam("page_no") int pageNo,
      @RequestParam("page_size") int pageSize) {
    Page<EsbRcptModel> data = asnService.findAsnByPage(pageNo, pageSize);
    return ResponseEntity.ok().body(BaseResponseDto.ok(data));
  }

  @Value("${env}")
  private String env;
}
