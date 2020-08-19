package com.example.poc.service.impl;

import com.example.poc.dto.AsnDto;
import com.example.poc.dto.RcptShipPoDetailDto;
import com.example.poc.dto.RcptShipPoDto;
import com.example.poc.mapper.RcptShipCartonDetailMapper;
import com.example.poc.mapper.RcptShipMapper;
import com.example.poc.mapper.RcptShipPoDetailMapper;
import com.example.poc.mapper.RcptShipPoMapper;
import com.example.poc.model.RcptShipModel;
import com.example.poc.model.RcptShipPoDetailModel;
import com.example.poc.model.RcptShipPoModel;
import com.example.poc.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
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
    RcptShipModel rcptShipModel =
        composeRcptShipPoModel(asnDto);
    rcptShipMapper.insert(rcptShipModel);

    List<RcptShipPoModel> rcptShipPoModelList = new ArrayList<>();
    List<RcptShipPoDetailModel> rcptShipPoDetailModelList = new ArrayList<>();

    if (Objects.nonNull(asnDto.getPoList()) &&
        asnDto.getPoList().size() > 0) {
      asnDto.getPoList().stream().forEach(
          rcptShipPoDto -> {
            RcptShipPoModel rcptShipPoModel =
                composeRcptShipPoModel(asnDto, rcptShipPoDto);
            rcptShipPoModelList.add(rcptShipPoModel);

            if (Objects.nonNull(rcptShipPoDto.getDetailList()) &&
                rcptShipPoDto.getDetailList().size() > 0) {
              rcptShipPoDto.getDetailList().stream().forEach(rcptShipPoDetailDto -> {
                RcptShipPoDetailModel rcptShipPoDetailModel =
                    composeRcptShipPoDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
                rcptShipPoDetailModelList.add(rcptShipPoDetailModel);
              });

              rcptShipPoDetailMapper.insertBatch(rcptShipPoDetailModelList);
            }
          });
      rcptShipPoMapper.insertBatch(rcptShipPoModelList);
    }
  }

  private RcptShipModel composeRcptShipPoModel(AsnDto asnDto) {
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

    return rcptShipModel;
  }

  private RcptShipPoModel composeRcptShipPoModel(
      AsnDto asnDto, RcptShipPoDto rcptShipPoDto) {
    RcptShipPoModel rcptShipPoModel = new RcptShipPoModel();
    rcptShipPoModel.setWhId(asnDto.getWhId());
    rcptShipPoModel.setShipmentNumber(asnDto.getShipmentNumber());
    rcptShipPoModel.setPoNumber(rcptShipPoDto.getPoNumber());
    rcptShipPoModel.setCasesExpected(rcptShipPoDto.getCasesExpected());
    rcptShipPoModel.setCasesReceived(rcptShipPoDto.getCasesReceived());
    rcptShipPoModel.setOpenToBuyDate(rcptShipPoDto.getOpenToBuyDate());

    return rcptShipPoModel;
  }

  private RcptShipPoDetailModel composeRcptShipPoDetailModel(
      AsnDto asnDto, RcptShipPoDto rcptShipPoDto, RcptShipPoDetailDto rcptShipPoDetailDto) {
    RcptShipPoDetailModel rcptShipPoDetailModel = new RcptShipPoDetailModel();
    rcptShipPoDetailModel.setWhId(asnDto.getWhId());
    rcptShipPoDetailModel.setShipmentNumber(asnDto.getShipmentNumber());
    rcptShipPoDetailModel.setPoNumber(rcptShipPoDto.getPoNumber());
    rcptShipPoDetailModel.setLineNumber(rcptShipPoDetailDto.getLineNumber());
    rcptShipPoDetailModel.setItemNumber(rcptShipPoDetailDto.getItemNumber());
    rcptShipPoDetailModel.setScheduleNumber(rcptShipPoDetailDto.getScheduleNumber());
    rcptShipPoDetailModel.setExpectedQty(rcptShipPoDetailDto.getExpectedQty());
    rcptShipPoDetailModel.setReceivedQty(rcptShipPoDetailDto.getReceivedQty());
    rcptShipPoDetailModel.setReconciledDate(rcptShipPoDetailDto.getReconciledDate());
    rcptShipPoDetailModel.setStatus(rcptShipPoDetailDto.getStatus());
    rcptShipPoDetailModel.setShipmentLineNumber(rcptShipPoDetailDto.getShipmentLineNumber());
    rcptShipPoDetailModel.setWeight(rcptShipPoDetailDto.getWeight());
    rcptShipPoDetailModel.setCalculationUom(rcptShipPoDetailDto.getCalculationUom());
    rcptShipPoDetailModel.setPropertyMark(rcptShipPoDetailDto.getPropertyMark());
    rcptShipPoDetailModel.setGenericField1(rcptShipPoDetailDto.getGenericField1());
    rcptShipPoDetailModel.setGenericField2(rcptShipPoDetailDto.getGenericField2());
    rcptShipPoDetailModel.setGenericField3(rcptShipPoDetailDto.getGenericField3());
    rcptShipPoDetailModel.setGenericField4(rcptShipPoDetailDto.getGenericField4());
    rcptShipPoDetailModel.setGenericField5(rcptShipPoDetailDto.getGenericField5());
    rcptShipPoDetailModel.setGenericField6(rcptShipPoDetailDto.getGenericField6());
    rcptShipPoDetailModel.setGenericField7(rcptShipPoDetailDto.getGenericField7());
    rcptShipPoDetailModel.setGenericField8(rcptShipPoDetailDto.getGenericField8());
    rcptShipPoDetailModel.setGenericField9(rcptShipPoDetailDto.getGenericField9());
    rcptShipPoDetailModel.setGenericField10(rcptShipPoDetailDto.getGenericField10());
    rcptShipPoDetailModel.setGenericField11(rcptShipPoDetailDto.getGenericField11());
    rcptShipPoDetailModel.setGenericField12(rcptShipPoDetailDto.getGenericField12());
    rcptShipPoDetailModel.setGenericField13(rcptShipPoDetailDto.getGenericField13());
    rcptShipPoDetailModel.setGenericField14(rcptShipPoDetailDto.getGenericField14());
    rcptShipPoDetailModel.setGenericField15(rcptShipPoDetailDto.getGenericField15());

    return rcptShipPoDetailModel;
  }
}
