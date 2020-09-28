package com.maersk.asia.wms.standard.dc.service.impl;

import com.maersk.asia.wms.standard.dc.component.enums.ReceiptStatusEnum;
import com.maersk.asia.wms.standard.dc.component.enums.ShipmentStatusEnum;
import com.maersk.asia.wms.standard.dc.mapper.*;
import com.maersk.asia.wms.standard.dc.model.*;
import com.maersk.commons.component.exception.BusinessException;
import com.maersk.commons.component.util.ErrorUtil;
import com.maersk.asia.wms.standard.dc.component.constant.MessageConstant;
import com.maersk.asia.wms.standard.dc.dto.AsnDto;
import com.maersk.asia.wms.standard.dc.dto.RcptShipPoDetailCartonDto;
import com.maersk.asia.wms.standard.dc.dto.RcptShipPoDetailDto;
import com.maersk.asia.wms.standard.dc.dto.RcptShipPoDto;
import com.maersk.asia.wms.standard.dc.service.CarrierService;
import com.maersk.asia.wms.standard.dc.service.AsnService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AsnServiceImpl implements AsnService {

  private final CarrierService carrierService;
  private final RcptShipMapper rcptShipMapper;
  private final RcptShipPoMapper rcptShipPoMapper;
  private final RcptShipPoDetailMapper rcptShipPoDetailMapper;
  private final RcptShipCartonDetailMapper rcptShipCartonDetailMapper;
  private final ReceiptMapper receiptMapper;
  private final ErrorUtil errorUtil;

  public AsnServiceImpl(
      CarrierService carrierService,
      RcptShipMapper rcptShipMapper,
      RcptShipPoMapper rcptShipPoMapper,
      RcptShipPoDetailMapper rcptShipPoDetailMapper,
      RcptShipCartonDetailMapper rcptShipCartonDetailMapper,
      ReceiptMapper receiptMapper,
      ErrorUtil errorUtil) {
    this.carrierService = carrierService;
    this.rcptShipMapper = rcptShipMapper;
    this.rcptShipPoMapper = rcptShipPoMapper;
    this.rcptShipPoDetailMapper = rcptShipPoDetailMapper;
    this.rcptShipCartonDetailMapper = rcptShipCartonDetailMapper;
    this.receiptMapper = receiptMapper;
    this.errorUtil = errorUtil;
  }

  @Override
  @Transactional
  public AsnDto retrieve(String whId, String shipmentNumber) {
    List<RcptShipModel> rcptShipModels =
        rcptShipMapper.selectDetailByWhIdAndShipmentNumber(whId, shipmentNumber);

    AsnDto asnDto = new AsnDto();
    if (Objects.isNull(rcptShipModels) || rcptShipModels.size() == 0) {
      return asnDto;
    }
    BeanUtils.copyProperties(rcptShipModels.get(0), asnDto);
    return asnDto;
  }

  @Override
  @Transactional
  public void create(AsnDto asnDto) {
    if (isShipmentExisted(asnDto.getWhId(), asnDto.getShipmentNumber())) {
      throw new BusinessException(
          errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_0009));
    }

    RcptShipModel rcptShipModel = new RcptShipModel();
    List<RcptShipPoModel> rcptShipPoModelList = new ArrayList<>();
    List<RcptShipPoDetailModel> rcptShipPoDetailModelList = new ArrayList<>();
    List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList = new ArrayList<>();
    composeRcptShipInfo(
        asnDto,
        rcptShipModel,
        rcptShipPoModelList,
        rcptShipPoDetailModelList,
        rcptShipCartonDetailModelList);

    rcptShipMapper.insert(rcptShipModel);
    rcptShipPoMapper.bulkInsert(rcptShipPoModelList);
    rcptShipPoDetailMapper.bulkInsert(rcptShipPoDetailModelList);
    rcptShipCartonDetailMapper.bulkInsert(rcptShipCartonDetailModelList);
  }

  @Override
  @Transactional
  public int update(AsnDto asnDto) {
    validateShipmentAllowed2Modify(asnDto.getWhId(), asnDto.getShipmentNumber());

    RcptShipModel rcptShipModel = new RcptShipModel();
    List<RcptShipPoModel> rcptShipPoModelList = new ArrayList<>();
    List<RcptShipPoDetailModel> rcptShipPoDetailModelList = new ArrayList<>();
    List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList = new ArrayList<>();
    composeRcptShipInfo(
        asnDto,
        rcptShipModel,
        rcptShipPoModelList,
        rcptShipPoDetailModelList,
        rcptShipCartonDetailModelList);

    int result = rcptShipMapper.update(rcptShipModel);
    rcptShipPoMapper.bulkUpdate(rcptShipPoModelList);
    rcptShipPoDetailMapper.bulkUpdate(rcptShipPoDetailModelList);
    rcptShipCartonDetailMapper.bulkUpdate(rcptShipCartonDetailModelList);

    return result;
  }

  @Override
  @Transactional
  public int delete(String whId, String shipmentNumber, String clientCode) {
    validateShipmentAllowed2Modify(whId, shipmentNumber);

    int result = rcptShipMapper.deleteByWhIdAndShipmentNumber(whId, shipmentNumber);
    rcptShipPoMapper.deleteByWhIdAndShipmentNumber(whId, shipmentNumber);
    rcptShipPoDetailMapper.deleteByWhIdAndShipmentNumber(whId, shipmentNumber);
    rcptShipCartonDetailMapper.deleteByWhIdAndShipmentNumber(whId, shipmentNumber);

    return result;
  }

  @Override
  @Transactional
  public void merge(AsnDto asnDto) {
    if (isShipmentExisted(asnDto.getWhId(), asnDto.getShipmentNumber())) {
      update(asnDto);
    } else {
      create(asnDto);
    }
  }

  @Override
  @Transactional
  public void renew(AsnDto asnDto) {
    delete(asnDto.getWhId(), asnDto.getShipmentNumber(), asnDto.getClientCode());
    create(asnDto);
  }

  private void composeRcptShipInfo(
      AsnDto asnDto,
      RcptShipModel rcptShipModel,
      List<RcptShipPoModel> rcptShipPoModelList,
      List<RcptShipPoDetailModel> rcptShipPoDetailModelList,
      List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList) {
    composeRcptShipModel(asnDto, rcptShipModel);

    if (Objects.nonNull(asnDto.getRcptShipPoList()) &&
        asnDto.getRcptShipPoList().size() > 0) {
      asnDto.getRcptShipPoList().stream().forEach(
          rcptShipPoDto -> {
            RcptShipPoModel rcptShipPoModel =
                composeRcptShipPoModel(asnDto, rcptShipPoDto);
            rcptShipPoModelList.add(rcptShipPoModel);

            if (Objects.nonNull(rcptShipPoDto.getRcptShipPoDetailList()) &&
                rcptShipPoDto.getRcptShipPoDetailList().size() > 0) {
              rcptShipPoDto.getRcptShipPoDetailList().stream().forEach(rcptShipPoDetailDto -> {
                RcptShipPoDetailModel rcptShipPoDetailModel =
                    composeRcptShipPoDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
                rcptShipPoDetailModelList.add(rcptShipPoDetailModel);

                if (Objects.nonNull(rcptShipPoDetailDto.getRcptShipPoDetailCartonList()) &&
                    rcptShipPoDetailDto.getRcptShipPoDetailCartonList().size() > 0) {
                  rcptShipPoDetailDto.getRcptShipPoDetailCartonList().stream().forEach(rcptShipPoDetailCartonDto -> {
                    RcptShipCartonDetailModel rcptShipCartonDetailModel =
                        composeRcptShipCartonDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto, rcptShipPoDetailCartonDto);
                    rcptShipCartonDetailModelList.add(rcptShipCartonDetailModel);
                  });
                }
              });
            }
          });
    }
  }

  private RcptShipModel composeRcptShipModel(AsnDto asnDto) {
    RcptShipModel rcptShipModel = new RcptShipModel();
    composeRcptShipModel(asnDto, rcptShipModel);

    return rcptShipModel;
  }

  private void composeRcptShipModel(
      AsnDto asnDto, RcptShipModel rcptShipModel) {
    rcptShipModel.setWhId(asnDto.getWhId());
    rcptShipModel.setShipmentNumber(asnDto.getShipmentNumber());
    List<CarrierModel> carrierModels =
        carrierService.retrieveByCarrierName(asnDto.getCarrierName());
    if (Objects.isNull(carrierModels) || carrierModels.size() == 0) {
      throw new BusinessException(
          errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_0008));
    }
    rcptShipModel.setCarrierId(carrierModels.get(0).getCarrierId());
    rcptShipModel.setTrailerNumber(asnDto.getTrailerNumber());
    if (Objects.isNull(asnDto.getDateExpected())) {
      asnDto.setDateExpected(Instant.now());
    }
    rcptShipModel.setDateExpected(asnDto.getDateExpected());
    if (Objects.isNull(asnDto.getDateReceived())) {
      asnDto.setDateReceived(Instant.now());
    }
    rcptShipModel.setDateReceived(asnDto.getDateReceived());
    if (Objects.isNull(asnDto.getDateShipped())) {
      asnDto.setDateShipped(Instant.now());
    }
    rcptShipModel.setDateShipped(asnDto.getDateShipped());
    if (StringUtils.isEmpty(asnDto.getStatus())) {
      asnDto.setStatus(ShipmentStatusEnum.SHIPMENT_STATUS_CODE_OPEN.getCode());
    }
    rcptShipModel.setStatus(asnDto.getStatus());
    rcptShipModel.setComments(asnDto.getComments());
    rcptShipModel.setWorkersAssigned(asnDto.getWorkersAssigned());
    rcptShipModel.setProNumber(asnDto.getProNumber());
    rcptShipModel.setArrivalSign(asnDto.getArrivalSign());
    rcptShipModel.setArrivalDate(asnDto.getArrivalDate());
    rcptShipModel.setGrnSendSign(asnDto.getGrnSendSign());
    rcptShipModel.setGrnSendDate(asnDto.getGrnSendDate());
    rcptShipModel.setShipmentReceiptDate(asnDto.getShipmentReceiptDate());
    rcptShipModel.setAsnType(asnDto.getAsnType());
    rcptShipModel.setWipNumber(asnDto.getWipNumber());
    rcptShipModel.setFileSeq(asnDto.getFileSeq());
    rcptShipModel.setTallysheetIsPrinted(asnDto.getTallysheetIsPrinted());
  }

  private RcptShipPoModel composeRcptShipPoModel(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto) {
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
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto,
      RcptShipPoDetailDto rcptShipPoDetailDto) {
    RcptShipPoDetailModel rcptShipPoDetailModel = new RcptShipPoDetailModel();
    rcptShipPoDetailModel.setWhId(asnDto.getWhId());
    rcptShipPoDetailModel.setShipmentNumber(asnDto.getShipmentNumber());
    rcptShipPoDetailModel.setPoNumber(rcptShipPoDto.getPoNumber());
    rcptShipPoDetailModel.setLineNumber(rcptShipPoDetailDto.getLineNumber());
    rcptShipPoDetailModel.setItemNumber(rcptShipPoDetailDto.getItemNumber());
    if (Objects.isNull(rcptShipPoDetailDto.getScheduleNumber())) {
      rcptShipPoDetailDto.setScheduleNumber(1);
    }
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
    rcptShipPoDetailModel.setGrnSendSign(rcptShipPoDetailDto.getGrnSendSign());
    rcptShipPoDetailModel.setGrnSendDate(rcptShipPoDetailDto.getGrnSendDate());
    rcptShipPoDetailModel.setCustomsIsApproved(rcptShipPoDetailDto.getCustomsIsApproved());
    rcptShipPoDetailModel.setBondedQty(rcptShipPoDetailDto.getBondedQty());
    rcptShipPoDetailModel.setFreeQty(rcptShipPoDetailDto.getFreeQty());
    rcptShipPoDetailModel.setConfirmBounded(rcptShipPoDetailDto.getConfirmBounded());
    rcptShipPoDetailModel.setConfirmFree(rcptShipPoDetailDto.getConfirmFree());
    rcptShipPoDetailModel.setConfirmKit(rcptShipPoDetailDto.getConfirmKit());
    rcptShipPoDetailModel.setReceivedBondedQty(rcptShipPoDetailDto.getReceivedBondedQty());
    rcptShipPoDetailModel.setReceivedFreeQty(rcptShipPoDetailDto.getReceivedFreeQty());

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
    rcptShipCartonDetailModel.setScheduleNumber(rcptShipPoDetailDto.getScheduleNumber());
    rcptShipCartonDetailModel.setExpectQty(rcptShipPoDetailCartonDto.getExpectQty());
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
    rcptShipCartonDetailModel.setSendToLocalDb(rcptShipPoDetailCartonDto.getSendToLocalDb());
    rcptShipCartonDetailModel.setSendDate(rcptShipPoDetailCartonDto.getSendDate());
    rcptShipCartonDetailModel.setContainerLabel(rcptShipPoDetailCartonDto.getContainerLabel());
    rcptShipCartonDetailModel.setPickingListId(rcptShipPoDetailCartonDto.getPickingListId());
    rcptShipCartonDetailModel.setScanFlag(rcptShipPoDetailCartonDto.getScanFlag());
    rcptShipCartonDetailModel.setQtyReceived(rcptShipPoDetailCartonDto.getQtyReceived());

    return rcptShipCartonDetailModel;
  }

  private void validateShipmentAllowed2Modify(String whId, String shipmentNumber) {
    List<RcptShipModel> rcptShipModels =
        retrieveShipmentByWhIdAndShipmentNumber(
            whId, shipmentNumber);

    if (!isShipmentExisted(rcptShipModels)) {
      throw new BusinessException(
          errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_0004));
    }

    if (isShipmentClosed(rcptShipModels.get(0))) {
      throw new BusinessException(
          errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_0005));
    }

    if (isShipmentReconciled(rcptShipModels.get(0))) {
      throw new BusinessException(
          errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_0006));
    }

    if (isShipmentUnderReceiving(whId, shipmentNumber)) {
      throw new BusinessException(
          errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_0007));
    }
  }

  private boolean isShipmentExisted(String whId, String shipmentNumber) {
    List<RcptShipModel> rcptShipModels =
        rcptShipMapper.selectByWhIdAndShipmentNumber(whId, shipmentNumber);

    return isShipmentExisted(rcptShipModels);
  }

  private boolean isShipmentExisted(List<RcptShipModel> rcptShipModels) {
    if (Objects.isNull(rcptShipModels) || rcptShipModels.size() == 0) {
      return false;
    }
    return true;
  }

  private boolean isShipmentClosed(RcptShipModel rcptShipModel) {
    if (ShipmentStatusEnum
        .SHIPMENT_STATUS_CODE_CLOSED
        .getCode().equals(rcptShipModel.getStatus())) {
      return true;
    }
    return false;
  }

  private boolean isShipmentReconciled(RcptShipModel rcptShipModel) {
    if (ShipmentStatusEnum
        .SHIPMENT_STATUS_CODE_RECONCILED
        .getCode().equals(rcptShipModel.getStatus())) {
      return true;
    }
    return false;
  }

  private boolean isShipmentUnderReceiving(String whId, String shipmentNumber) {
    int count =
        receiptMapper.selectCountByWhIdAndShipmentNumberAndStatus(
            whId, shipmentNumber, ReceiptStatusEnum.RECEIPT_STATUS_CODE_OPEN.getCode());
    if (count == 0) {
      return false;
    }
    return true;
  }

  private List<RcptShipModel> retrieveShipmentByWhIdAndShipmentNumber(
      String whId, String shipmentNumber) {
    return rcptShipMapper.selectByWhIdAndShipmentNumber(whId, shipmentNumber);
  }
}
