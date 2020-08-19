package com.example.poc.service.impl;

import com.example.poc.dto.AsnDto;
import com.example.poc.mapper.RcptShipCartonDetailMapper;
import com.example.poc.mapper.RcptShipMapper;
import com.example.poc.mapper.RcptShipPoDetailMapper;
import com.example.poc.mapper.RcptShipPoMapper;
import com.example.poc.model.RcptShipModel;
import com.example.poc.model.RcptShipPoModel;
import com.example.poc.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Objects;

@Slf4j
@Service
public class AsnServiceImpl implements AsnService {

  private final RcptShipMapper rcptShipMapper;
  private final RcptShipPoMapper rcptShipPoMapper;
  private final RcptShipPoDetailMapper rcptShipPoDetailMapper;
  private final RcptShipCartonDetailMapper rcptShipCartonDetailMapper;

  public AsnServiceImpl(
      RcptShipMapper rcptShipMapper,
      RcptShipPoMapper rcptShipPoMapper,
      RcptShipPoDetailMapper rcptShipPoDetailMapper,
      RcptShipCartonDetailMapper rcptShipCartonDetailMapper) {
    this.rcptShipMapper = rcptShipMapper;
    this.rcptShipPoMapper = rcptShipPoMapper;
    this.rcptShipPoDetailMapper = rcptShipPoDetailMapper;
    this.rcptShipCartonDetailMapper = rcptShipCartonDetailMapper;
  }

  @Override
  @Transactional
  public void createAsn(AsnDto asnDto) {
    RcptShipModel rcptShipModel = new RcptShipModel();
    rcptShipModel.setWhId(asnDto.getWhId());
    rcptShipModel.setShipmentNumber(asnDto.getShipmentNumber());
    rcptShipModel.setCarrierId(83);
    rcptShipModel.setTrailerNumber(asnDto.getTrailerNumber());
    rcptShipModel.setDateExpected(asnDto.getDateExpected());
    rcptShipModel.setDateReceived(asnDto.getDateReceived());
    rcptShipModel.setDateShipped(asnDto.getDateShipped());
    rcptShipModel.setStatus(asnDto.getStatus());
    rcptShipModel.setComments(asnDto.getComments());
    rcptShipModel.setWorkersAssigned(asnDto.getWorkersAssigned());
    rcptShipModel.setProNumber(asnDto.getProNumber());
    rcptShipModel.setArrivalSign(asnDto.getArrivalSign());
    rcptShipModel.setArrivalDate(asnDto.getArrivalDate());
    rcptShipModel.setGrnSendSign(asnDto.getGrnSendSign());
    rcptShipModel.setGrnSendDate(asnDto.getGrnSendDate());
    rcptShipMapper.insert(rcptShipModel);

    if (Objects.nonNull(asnDto.getPoList())) {
      asnDto.getPoList().stream().forEach(
          rcptShipPoDto -> {
            RcptShipPoModel rcptShipPoModel = new RcptShipPoModel();
            rcptShipPoModel.setWhId(asnDto.getWhId());
            rcptShipPoModel.setShipmentNumber(asnDto.getShipmentNumber());
            rcptShipPoModel.setPoNumber(rcptShipPoDto.getPoNumber());
            rcptShipPoModel.setCasesExpected(rcptShipPoDto.getCasesExpected());
            rcptShipPoModel.setCasesReceived(rcptShipPoDto.getCasesReceived());
            rcptShipPoModel.setOpenToBuyDate(rcptShipPoDto.getOpenToBuyDate());
            rcptShipPoMapper.insert(rcptShipPoModel);
          });
    }
  }

}
