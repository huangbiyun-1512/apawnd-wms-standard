package com.example.poc.controller.v1;

import com.common.poc.components.annotation.AutoLogging;
import com.example.poc.api.InboundApi;
import com.example.poc.dto.PoDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("api/v1/inbound")
public class InboundController implements InboundApi {

  @Override
  @PostMapping(value = "/purchase_orders", produces = MediaType.APPLICATION_JSON_VALUE)
  @AutoLogging
  public ResponseEntity createPurchaseOrder(@Valid @RequestBody PoDto poDto) {
    log.info("po_number: {}", poDto.getPoNumber());
    log.info("create_date: {}", poDto.getCreateDate());
    log.info("order_pcs: {}", poDto.getOrderPcs());
    log.info("lp_sequence: {}", poDto.getLpSequence());
    log.info("generic_field1: {}", poDto.getGenericField1());
    return ResponseEntity.created(null).build();
  }
}
