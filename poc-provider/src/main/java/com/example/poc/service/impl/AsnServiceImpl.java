package com.example.poc.service.impl;

import com.example.poc.dto.AsnDto;
import com.example.poc.model.EsbRcptModel;
import com.example.poc.repository.AsnRepository;
import com.example.poc.service.AsnService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AsnServiceImpl implements AsnService {

  private final AsnRepository asnRepository;

  public AsnServiceImpl(
      AsnRepository asnRepository) {
    this.asnRepository = asnRepository;
  }

  @Override
  public void createAsn(AsnDto asnDto) {
    EsbRcptModel esbRcptModel = new EsbRcptModel();
    esbRcptModel.setHjsSequence(1);
    esbRcptModel.setHjsErrorNumber(-1);
    asnRepository.insert(esbRcptModel);
  }

  @Override
  public Page<EsbRcptModel> findAsnByPage(int pageNo, int pageSize) {
    PageHelper.startPage(pageNo, pageSize);
    return asnRepository.findByPage();
  }
}
