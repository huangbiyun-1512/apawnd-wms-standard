package com.example.poc.service.impl;

import com.example.poc.dto.AsnDto;
import com.example.poc.mapper.EsbRcptMapper;
import com.example.poc.model.EsbRcptModel;
import com.example.poc.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsnServiceImpl implements AsnService {

  private final EsbRcptMapper esbRcptMapper;

  public AsnServiceImpl(
      EsbRcptMapper esbRcptMapper) {
    this.esbRcptMapper = esbRcptMapper;
  }

  @Override
  public void createAsn(AsnDto asnDto) {
    EsbRcptModel esbRcptModel = new EsbRcptModel();
    esbRcptModel.setHjsSequence(1);
    esbRcptModel.setHjsErrorNumber(-1);
    esbRcptMapper.insert(esbRcptModel);
  }

}
