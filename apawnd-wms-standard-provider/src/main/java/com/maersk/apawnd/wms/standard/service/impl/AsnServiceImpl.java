package com.maersk.apawnd.wms.standard.service.impl;

import com.maersk.apawnd.commons.component.exception.BusinessException;
import com.maersk.apawnd.commons.component.util.ErrorUtil;
import com.maersk.apawnd.wms.standard.component.constant.MessageConstant;
import com.maersk.apawnd.wms.standard.component.enums.ActionCodeEnum;
import com.maersk.apawnd.wms.standard.component.enums.ReceiptStatusEnum;
import com.maersk.apawnd.wms.standard.component.enums.ShipmentStatusEnum;
import com.maersk.apawnd.wms.standard.dto.AsnDto;
import com.maersk.apawnd.wms.standard.dto.RcptShipPoDetailCartonDto;
import com.maersk.apawnd.wms.standard.dto.RcptShipPoDetailDto;
import com.maersk.apawnd.wms.standard.dto.RcptShipPoDto;
import com.maersk.apawnd.wms.standard.mapper.*;
import com.maersk.apawnd.wms.standard.model.*;
import com.maersk.apawnd.wms.standard.service.AsnService;
import com.maersk.apawnd.wms.standard.service.CarrierService;
import com.maersk.apawnd.wms.standard.service.ItemService;
import com.maersk.apawnd.wms.standard.service.PoService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class AsnServiceImpl implements AsnService {

  private final PoService poService;
  private final ItemService itemService;
  private final CarrierService carrierService;
  private final RcptShipMapper rcptShipMapper;
  private final RcptShipPoMapper rcptShipPoMapper;
  private final RcptShipPoDetailMapper rcptShipPoDetailMapper;
  private final RcptShipCartonDetailMapper rcptShipCartonDetailMapper;
  private final ReceiptMapper receiptMapper;
  private final ErrorUtil errorUtil;

  public AsnServiceImpl(
      PoService poService,
      ItemService itemService,
      CarrierService carrierService,
      RcptShipMapper rcptShipMapper,
      RcptShipPoMapper rcptShipPoMapper,
      RcptShipPoDetailMapper rcptShipPoDetailMapper,
      RcptShipCartonDetailMapper rcptShipCartonDetailMapper,
      ReceiptMapper receiptMapper,
      ErrorUtil errorUtil) {
    this.poService = poService;
    this.itemService = itemService;
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
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0009));
    }

    rcptShipMapper.insert(composeRcptShipModel(asnDto));
    insertRcptPoRefers(asnDto);
  }

  @Override
  @Transactional
  public int update(AsnDto asnDto) {
    validateShipmentAllowed2Modify(asnDto.getWhId(), asnDto.getShipmentNumber());

    int result = rcptShipMapper.update(composeRcptShipModel(asnDto));
    updateRcptShipPoRefersByActionCode(asnDto);

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
  public boolean merge(AsnDto asnDto) {
    if (isShipmentExisted(
        asnDto.getWhId(),
        asnDto.getShipmentNumber())) {
      update(asnDto);
      return true;
    }
    create(asnDto);
    return false;
  }

  @Override
  @Transactional
  public void replace(AsnDto asnDto) {
    delete(asnDto.getWhId(), asnDto.getShipmentNumber(), asnDto.getClientCode());
    create(asnDto);
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

  private List<RcptShipPoDetailModel> composeRcptShipPoDetailModelList(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto) {
    List<RcptShipPoDetailModel> rcptShipPoDetailModelList = new ArrayList<>();
    if (Objects.nonNull(rcptShipPoDto) && Objects.nonNull(rcptShipPoDto.getRcptShipPoDetailList())) {
      rcptShipPoDto.getRcptShipPoDetailList().stream().forEach(rcptShipPoDetailDto -> {
        rcptShipPoDetailModelList.add(
            composeRcptShipPoDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto));
      });
    }
    return rcptShipPoDetailModelList;
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
    if (Objects.nonNull(rcptShipPoDetailDto.getWeight())) {
      if (Objects.isNull(rcptShipPoDetailDto.getExpectedQty()) ||
          BigDecimal.ZERO.compareTo(rcptShipPoDetailDto.getExpectedQty()) == 0) {
        rcptShipPoDetailModel.setWeight(rcptShipPoDetailDto.getExpectedQty());
      } else {
        rcptShipPoDetailModel.setWeight(
            rcptShipPoDetailDto.getWeight().divide(rcptShipPoDetailDto.getExpectedQty()));
      }
    }
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

  private List<RcptShipCartonDetailModel> composeRcptShipCartonDetailModelList(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto) {
    List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList = new ArrayList<>();
    if (Objects.nonNull(rcptShipPoDto) && Objects.nonNull(rcptShipPoDto.getRcptShipPoDetailList())) {
      rcptShipPoDto.getRcptShipPoDetailList().stream().forEach(rcptShipPoDetailDto -> {
        if (Objects.nonNull(rcptShipPoDetailDto) && Objects.nonNull(rcptShipPoDetailDto.getRcptShipPoDetailCartonList())) {
          rcptShipPoDetailDto.getRcptShipPoDetailCartonList().stream().forEach(rcptShipPoDetailCartonDto -> {
            rcptShipCartonDetailModelList.add(
                composeRcptShipCartonDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto, rcptShipPoDetailCartonDto));
          });
        }
      });
    }

    return  rcptShipCartonDetailModelList;
  }

  private List<RcptShipCartonDetailModel> composeRcptShipCartonDetailModelList(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto,
      RcptShipPoDetailDto rcptShipPoDetailDto) {
    List<RcptShipCartonDetailModel> rcptShipCartonDetailModelList = new ArrayList<>();
    if (Objects.nonNull(rcptShipPoDetailDto) && Objects.nonNull(rcptShipPoDetailDto.getRcptShipPoDetailCartonList())) {
      rcptShipPoDetailDto.getRcptShipPoDetailCartonList().stream().forEach(rcptShipPoDetailCartonDto -> {
        rcptShipCartonDetailModelList.add(
            composeRcptShipCartonDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto, rcptShipPoDetailCartonDto));
      });
    }

    return  rcptShipCartonDetailModelList;
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
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0004));
    }

    if (isShipmentClosed(rcptShipModels.get(0))) {
      throw new BusinessException(
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0005));
    }

    if (isShipmentReconciled(rcptShipModels.get(0))) {
      throw new BusinessException(
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0006));
    }

    if (isShipmentUnderReceiving(whId, shipmentNumber)) {
      throw new BusinessException(
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0007));
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

  private void updateRcptShipPoRefersByActionCode(
      AsnDto asnDto) {
    if (Objects.nonNull(asnDto) && Objects.nonNull(asnDto.getRcptShipPoList())) {
      asnDto.getRcptShipPoList().stream().forEach(rcptShipPoDto -> {
        switch (ActionCodeEnum.valueOf(rcptShipPoDto.getActionCode())) {
          case ACTION_CODE_CREATE:
            insertRcptPoRefers(asnDto, rcptShipPoDto);
            break;
          case ACTION_CODE_UPDATE:
            updateRcptPoRefers(asnDto, rcptShipPoDto);
            break;
          case ACTION_CODE_DELETE:
            deleteRcptPoRefers(asnDto, rcptShipPoDto);
            break;
          case ACTION_CODE_MERGE:
            if (0 == rcptShipPoMapper.selectCountByWhIdAndShipmentNumberAndPoNumber(
                asnDto.getWhId(),
                asnDto.getShipmentNumber(),
                rcptShipPoDto.getPoNumber())) {
              insertRcptPoRefers(asnDto, rcptShipPoDto);
            } else {
              updateRcptPoRefers(asnDto, rcptShipPoDto);
            }
            break;
          case ACTION_CODE_EXIST_BY_PASS:
            if (0 == rcptShipPoMapper.selectCountByWhIdAndShipmentNumberAndPoNumber(
                asnDto.getWhId(),
                asnDto.getShipmentNumber(),
                rcptShipPoDto.getPoNumber())) {
              insertRcptPoRefers(asnDto, rcptShipPoDto);
            }
            break;
          case ACTION_CODE_ADD_AFTER_DELETE:
            deleteRcptPoRefers(asnDto, rcptShipPoDto);
            insertRcptPoRefers(asnDto, rcptShipPoDto);
            break;
          default:
            throw new BusinessException(
                errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_0010));
        }
      });
    }
  }

  private void updateRcptShipPoDetailRefersByActionCode(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto) {
    if (Objects.nonNull(rcptShipPoDto) && Objects.nonNull(rcptShipPoDto.getRcptShipPoDetailList())) {
      rcptShipPoDto.getRcptShipPoDetailList().stream().forEach(rcptShipPoDetailDto -> {
        switch (ActionCodeEnum.valueOf(rcptShipPoDetailDto.getActionCode())) {
          case ACTION_CODE_CREATE:
            insertRcptPoDetailRefers(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
            break;
          case ACTION_CODE_UPDATE:
            updateRcptPoDetailRefers(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
            break;
          case ACTION_CODE_DELETE:
            deleteRcptPoDetailRefers(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
            break;
          case ACTION_CODE_MERGE:
            if (0 == rcptShipPoDetailMapper.selectCountByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
                asnDto.getWhId(),
                asnDto.getShipmentNumber(),
                rcptShipPoDto.getPoNumber(),
                rcptShipPoDetailDto.getLineNumber(),
                rcptShipPoDetailDto.getItemNumber(),
                rcptShipPoDetailDto.getScheduleNumber())) {
              insertRcptPoDetailRefers(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
            } else {
              updateRcptPoDetailRefers(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
            }
            break;
          case ACTION_CODE_EXIST_BY_PASS:
            if (0 == rcptShipPoDetailMapper.selectCountByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
                asnDto.getWhId(),
                asnDto.getShipmentNumber(),
                rcptShipPoDto.getPoNumber(),
                rcptShipPoDetailDto.getLineNumber(),
                rcptShipPoDetailDto.getItemNumber(),
                rcptShipPoDetailDto.getScheduleNumber())) {
              insertRcptPoDetailRefers(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
            }
            break;
          case ACTION_CODE_ADD_AFTER_DELETE:
            deleteRcptPoDetailRefers(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
            insertRcptPoDetailRefers(asnDto, rcptShipPoDto, rcptShipPoDetailDto);
            break;
          default:
            throw new BusinessException(
                errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_0010));
        }
      });
    }
  }

  private void insertRcptPoRefers(AsnDto asnDto) {
    if (Objects.nonNull(asnDto) && Objects.nonNull(asnDto.getRcptShipPoList())) {
      asnDto.getRcptShipPoList().stream().forEach(rcptShipPoDto -> {
        insertRcptPoRefers(asnDto, rcptShipPoDto);
      });
    }
  }

  private void insertRcptPoRefers(AsnDto asnDto, RcptShipPoDto rcptShipPoDto) {
    if (!poService.isPoExisted(asnDto.getWhId(), rcptShipPoDto.getPoNumber())) {
      throw new BusinessException(
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0011));
    }

    rcptShipPoMapper.insert(composeRcptShipPoModel(asnDto, rcptShipPoDto));
    rcptShipPoDetailMapper.bulkInsert(composeRcptShipPoDetailModelList(asnDto, rcptShipPoDto));
    rcptShipCartonDetailMapper.bulkInsert(composeRcptShipCartonDetailModelList(asnDto, rcptShipPoDto));
  }

  private void updateRcptPoRefers(AsnDto asnDto, RcptShipPoDto rcptShipPoDto) {
    if (!poService.isPoExisted(asnDto.getWhId(), rcptShipPoDto.getPoNumber())) {
      throw new BusinessException(
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0011));
    }

    rcptShipPoMapper.update(composeRcptShipPoModel(asnDto, rcptShipPoDto));
    updateRcptShipPoDetailRefersByActionCode(asnDto, rcptShipPoDto);
  }

  private void deleteRcptPoRefers(AsnDto asnDto, RcptShipPoDto rcptShipPoDto) {
    rcptShipPoMapper.deleteByWhIdAndShipmentNumberAndPoNumber(
        asnDto.getWhId(),
        asnDto.getShipmentNumber(),
        rcptShipPoDto.getPoNumber());

    rcptShipPoDetailMapper.deleteByWhIdAndShipmentNumberAndPoNumber(
        asnDto.getWhId(),
        asnDto.getShipmentNumber(),
        rcptShipPoDto.getPoNumber());

    rcptShipCartonDetailMapper.deleteByWhIdAndShipmentNumberAndPoNumber(
        asnDto.getWhId(),
        asnDto.getShipmentNumber(),
        rcptShipPoDto.getPoNumber());
  }

  private void insertRcptPoDetailRefers(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto,
      RcptShipPoDetailDto rcptShipPoDetailDto) {
    rcptShipPoDetailMapper.insert(
        composeRcptShipPoDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto));
    rcptShipCartonDetailMapper.bulkInsert(
        composeRcptShipCartonDetailModelList(asnDto, rcptShipPoDto, rcptShipPoDetailDto));
  }

  private void updateRcptPoDetailRefers(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto,
      RcptShipPoDetailDto rcptShipPoDetailDto) {
    if (!poService.isPoDetailExisted(
        asnDto.getWhId(),
        rcptShipPoDto.getPoNumber(),
        rcptShipPoDetailDto.getLineNumber(),
        rcptShipPoDetailDto.getItemNumber(),
        rcptShipPoDetailDto.getScheduleNumber())) {
      throw new BusinessException(
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0012));
    }

    if (rcptShipPoDetailMapper.selectCountByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
        asnDto.getWhId(),
        asnDto.getShipmentNumber(),
        rcptShipPoDto.getPoNumber(),
        rcptShipPoDetailDto.getLineNumber(),
        rcptShipPoDetailDto.getItemNumber(),
        rcptShipPoDetailDto.getScheduleNumber()) == 0) {
      throw new BusinessException(
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0012));
    }

    if (!itemService.isItemExisted(asnDto.getWhId(), rcptShipPoDetailDto.getItemNumber())) {
      throw new BusinessException(
          errorUtil.build409ErrorList(MessageConstant.MESSAGE_KEY_E01_0013));
    }

    rcptShipPoDetailMapper.update(composeRcptShipPoDetailModel(asnDto, rcptShipPoDto, rcptShipPoDetailDto));
    rcptShipCartonDetailMapper.deleteByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
        asnDto.getWhId(),
        asnDto.getShipmentNumber(),
        rcptShipPoDto.getPoNumber(),
        rcptShipPoDetailDto.getLineNumber(),
        rcptShipPoDetailDto.getItemNumber(),
        rcptShipPoDetailDto.getScheduleNumber());
    rcptShipCartonDetailMapper.bulkInsert(
        composeRcptShipCartonDetailModelList(asnDto, rcptShipPoDto, rcptShipPoDetailDto));
  }

  private void deleteRcptPoDetailRefers(
      AsnDto asnDto,
      RcptShipPoDto rcptShipPoDto,
      RcptShipPoDetailDto rcptShipPoDetailDto) {
    rcptShipPoDetailMapper.deleteByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
        asnDto.getWhId(),
        asnDto.getShipmentNumber(),
        rcptShipPoDto.getPoNumber(),
        rcptShipPoDetailDto.getLineNumber(),
        rcptShipPoDetailDto.getItemNumber(),
        rcptShipPoDetailDto.getScheduleNumber());
    rcptShipCartonDetailMapper.deleteByWhIdAndShipmentNumberAndPoNumberAndLineNumberAndItemNumberAndScheduleNumber(
        asnDto.getWhId(),
        asnDto.getShipmentNumber(),
        rcptShipPoDto.getPoNumber(),
        rcptShipPoDetailDto.getLineNumber(),
        rcptShipPoDetailDto.getItemNumber(),
        rcptShipPoDetailDto.getScheduleNumber());
  }
}
