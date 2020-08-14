package com.example.poc.service.impl;

import com.example.poc.dto.AsnDto;
import com.example.poc.mapper.AsnMapper;
import com.example.poc.model.EsbRcptModel;
import com.example.poc.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsnServiceImpl implements AsnService {

  private final AsnMapper asnMapper;

  public AsnServiceImpl(
      AsnMapper asnMapper) {
    this.asnMapper = asnMapper;
  }

  @Override
  public void createAsn(AsnDto asnDto) {
    EsbRcptModel esbRcptModel = new EsbRcptModel();
    esbRcptModel.setHjsSequence(1);
    esbRcptModel.setHjsErrorNumber(-1);
    asnMapper.insert(esbRcptModel);
  }

//  @Override
//  public IPage<EsbRcptModel> findAsnByPage(int pageNo, int pageSize) {
//    Page page = new Page();
//    page.setCurrent(pageNo);
//    page.setSize(pageSize);
//    return asnMapper.findByPage(page);
//  }
}
