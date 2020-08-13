package com.example.poc.service.impl;

import com.example.poc.component.util.SnowflakeUtils;
import com.example.poc.dto.AsnDto;
import com.example.poc.repository.AsnRepository;
import com.example.poc.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
public class AsnServiceImpl implements AsnService {

  private final AsnRepository asnRepository;
  private final SnowflakeUtils snowflakeUtils;

  public AsnServiceImpl(
      AsnRepository asnRepository,
      SnowflakeUtils snowflakeUtils) {
    this.asnRepository = asnRepository;
    this.snowflakeUtils = snowflakeUtils;
  }

  @Override
  public void createAsn(AsnDto asnDto) {
    asnRepository.insertAsn(asnDto);
  }
}
