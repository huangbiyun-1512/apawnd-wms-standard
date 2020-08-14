package com.example.poc.controller.v1;

import com.common.poc.components.annotation.AutoLogging;
import com.common.poc.components.dto.BaseResponseDto;
import com.example.poc.api.InboundApi;
import com.example.poc.dto.AsnDto;
import com.example.poc.service.AsnService;
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

  private final AsnService asnService;

  public InboundController(AsnService asnService) {
    this.asnService = asnService;
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

}
