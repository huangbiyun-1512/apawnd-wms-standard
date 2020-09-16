package com.maersk.highjump.generics.service.impl;

import com.maersk.commons.component.exception.BusinessException;
import com.maersk.highjump.generics.dto.AsnDto;
import com.maersk.highjump.generics.dto.RcptShipPoDetailCartonDto;
import com.maersk.highjump.generics.dto.RcptShipPoDetailDto;
import com.maersk.highjump.generics.dto.RcptShipPoDto;
import com.maersk.highjump.generics.mapper.RcptShipCartonDetailMapper;
import com.maersk.highjump.generics.mapper.RcptShipMapper;
import com.maersk.highjump.generics.mapper.RcptShipPoDetailMapper;
import com.maersk.highjump.generics.mapper.RcptShipPoMapper;
import com.maersk.highjump.generics.model.RcptShipCartonDetailModel;
import com.maersk.highjump.generics.model.RcptShipModel;
import com.maersk.highjump.generics.model.RcptShipPoDetailModel;
import com.maersk.highjump.generics.model.RcptShipPoModel;
import com.maersk.highjump.generics.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
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
    List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList = new ArrayList<>();

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

                if (Objects.nonNull(rcptShipPoDetailDto.getCartonList()) &&
                    rcptShipPoDetailDto.getCartonList().size() > 0) {
                  rcptShipPoDetailDto.getCartonList().stream().forEach(rcptShipPoDetailCartonDto -> {
                    RcptShipCartonDetailModel rcptShipCartonDetailModel =
                        composeRcptShipCartonDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto, rcptShipPoDetailCartonDto);
                    rcptShipCartonDetailModelList.add(rcptShipCartonDetailModel);
                  });
                }
              });
            }
          });
      rcptShipPoMapper.insertBatch(rcptShipPoModelList);
      rcptShipPoDetailMapper.insertBatch(rcptShipPoDetailModelList);
      rcptShipCartonDetailMapper.insertBatch(rcptShipCartonDetailModelList);
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
    rcptShipPoDetailModel.setConfirmBounded(BigDecimal.ZERO);
    rcptShipPoDetailModel.setConfirmFree(BigDecimal.ZERO);
    rcptShipPoDetailModel.setConfirmKit(BigDecimal.ZERO);

    return rcptShipPoDetailModel;
  }

  private RcptShipCartonDetailModel composeRcptShipCartonDetailModel(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto,
      RcptShipPoDetailDto rcptShipPoDetailDto,
      RcptShipPoDetailCartonDto rcptShipPoDetailCartonDto) {
    RcptShipCartonDetailModel rcptShipCartonDetailModel = new RcptShipCartonDetailModel();
    rcptShipCartonDetailModel.setWhId(asnDto.getWhId());
    rcptShipCartonDetailModel.setShipmentNumber(asnDto.getShipmentNumber());
    rcptShipCartonDetailModel.setPoNumber(rcptShipPoDto.getPoNumber());
    rcptShipCartonDetailModel.setLineNumber(rcptShipPoDetailDto.getLineNumber());
    rcptShipCartonDetailModel.setItemNumber(rcptShipPoDetailDto.getItemNumber());
    rcptShipCartonDetailModel.setExpectQty(rcptShipPoDetailCartonDto.getExpectQty());
    rcptShipCartonDetailModel.setScheduleNumber(1);
    rcptShipCartonDetailModel.setUcc(rcptShipPoDetailCartonDto.getUcc());
    rcptShipCartonDetailModel.setReference1(rcptShipPoDetailCartonDto.getReference1());
    rcptShipCartonDetailModel.setReference2(rcptShipPoDetailCartonDto.getReference2());
    rcptShipCartonDetailModel.setReference3(rcptShipPoDetailCartonDto.getReference3());
    rcptShipCartonDetailModel.setReference4(rcptShipPoDetailCartonDto.getReference4());
    rcptShipCartonDetailModel.setReference5(rcptShipPoDetailCartonDto.getReference5());
    rcptShipCartonDetailModel.setStatus(rcptShipPoDetailCartonDto.getStatus());
    rcptShipCartonDetailModel.setForkId(rcptShipPoDetailCartonDto.getForkId());
    rcptShipCartonDetailModel.setDimension(rcptShipPoDetailCartonDto.getDimension());
    rcptShipCartonDetailModel.setHuId(rcptShipPoDetailCartonDto.getHuId());
    rcptShipCartonDetailModel.setGenericField1(rcptShipPoDetailCartonDto.getGenericField1());
    rcptShipCartonDetailModel.setGenericField2(rcptShipPoDetailCartonDto.getGenericField2());
    rcptShipCartonDetailModel.setGenericField3(rcptShipPoDetailCartonDto.getGenericField3());
    rcptShipCartonDetailModel.setGenericField4(rcptShipPoDetailCartonDto.getGenericField4());
    rcptShipCartonDetailModel.setGenericField5(rcptShipPoDetailCartonDto.getGenericField5());
    rcptShipCartonDetailModel.setGenericField6(rcptShipPoDetailCartonDto.getGenericField6());
    rcptShipCartonDetailModel.setGenericField7(rcptShipPoDetailCartonDto.getGenericField7());
    rcptShipCartonDetailModel.setGenericField8(rcptShipPoDetailCartonDto.getGenericField8());
    rcptShipCartonDetailModel.setGenericField9(rcptShipPoDetailCartonDto.getGenericField9());
    rcptShipCartonDetailModel.setGenericField10(rcptShipPoDetailCartonDto.getGenericField10());
    rcptShipCartonDetailModel.setGenericField11(rcptShipPoDetailCartonDto.getGenericField11());
    rcptShipCartonDetailModel.setGenericField12(rcptShipPoDetailCartonDto.getGenericField12());
    rcptShipCartonDetailModel.setGenericField13(rcptShipPoDetailCartonDto.getGenericField13());
    rcptShipCartonDetailModel.setGenericField14(rcptShipPoDetailCartonDto.getGenericField14());
    rcptShipCartonDetailModel.setGenericField15(rcptShipPoDetailCartonDto.getGenericField15());
    rcptShipCartonDetailModel.setSendToLocalDb(0);
    rcptShipCartonDetailModel.setScanFlag(0);

    return rcptShipCartonDetailModel;
  }

  @Override
  public void deleteAsn(String criteria) throws BusinessException {
    // do business operation here
  }
}
