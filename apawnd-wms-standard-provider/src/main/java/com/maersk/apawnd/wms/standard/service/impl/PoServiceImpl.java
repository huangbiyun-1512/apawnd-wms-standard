package com.maersk.apawnd.wms.standard.service.impl;

import com.maersk.apawnd.wms.standard.mapper.PoDetailMapper;
import com.maersk.apawnd.wms.standard.mapper.PoMasterMapper;
import com.maersk.apawnd.wms.standard.service.PoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class PoServiceImpl implements PoService {

  private final PoMasterMapper poMasterMapper;
  private final PoDetailMapper poDetailMapper;

  public PoServiceImpl(
      PoMasterMapper poMasterMapper,
      PoDetailMapper poDetailMapper) {
    this.poMasterMapper = poMasterMapper;
    this.poDetailMapper = poDetailMapper;
  }

  @Override
  public boolean isPoExisted(String whId, String poNumber) {
    if (poMasterMapper.selectCountByWhIdAndPoNumber(whId, poNumber) > 0) {
      return true;
    }
    return false;
  }

  @Override
  public boolean isPoDetailExisted(
      String whId, String poNumber,
      String lineNumber, String itemNumber, String scheduleNumber) {
    if (poDetailMapper.selectCountByWhIdAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
        whId, poNumber, lineNumber, itemNumber, scheduleNumber) > 0) {
      return true;
    }
    return false;
  }
}
