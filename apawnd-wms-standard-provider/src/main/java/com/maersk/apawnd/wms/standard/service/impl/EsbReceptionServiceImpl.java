package com.maersk.apawnd.wms.standard.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.apawnd.wms.standard.dto.EsbRcptDto;
import com.maersk.apawnd.wms.standard.dto.EsbRcptMainDto;
import com.maersk.apawnd.wms.standard.dto.EsbRcptMsgPreambleDto;
import com.maersk.apawnd.wms.standard.dto.EsbRcptSerialNoDto;
import com.maersk.apawnd.wms.standard.mapper.EsbRcptMainMapper;
import com.maersk.apawnd.wms.standard.mapper.EsbRcptMapper;
import com.maersk.apawnd.wms.standard.mapper.EsbRcptMsgPreambleMapper;
import com.maersk.apawnd.wms.standard.mapper.EsbRcptSerialNoMapper;
import com.maersk.apawnd.wms.standard.model.EsbRcptMainModel;
import com.maersk.apawnd.wms.standard.model.EsbRcptModel;
import com.maersk.apawnd.wms.standard.model.EsbRcptMsgPreambleModel;
import com.maersk.apawnd.wms.standard.model.EsbRcptSerialNoModel;
import com.maersk.apawnd.wms.standard.service.EsbReceptionService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class EsbReceptionServiceImpl implements EsbReceptionService {

  private final EsbRcptMapper esbRcptMapper;
  private final EsbRcptMainMapper esbRcptMainMapper;
  private final EsbRcptMsgPreambleMapper esbRcptMsgPreambleMapper;
  private final EsbRcptSerialNoMapper esbRcptSerialNoMapper;
  private final ObjectMapper objectMapper;

  public EsbReceptionServiceImpl(
      EsbRcptMapper esbRcptMapper,
      EsbRcptMainMapper esbRcptMainMapper,
      EsbRcptMsgPreambleMapper esbRcptMsgPreambleMapper,
      EsbRcptSerialNoMapper esbRcptSerialNoMapper,
      ObjectMapper objectMapper) {
    this.esbRcptMapper = esbRcptMapper;
    this.esbRcptMainMapper = esbRcptMainMapper;
    this.esbRcptMsgPreambleMapper = esbRcptMsgPreambleMapper;
    this.esbRcptSerialNoMapper = esbRcptSerialNoMapper;
    this.objectMapper = objectMapper;
  }

  @Override
  @Transactional
  public String generateJsonGrnAck(String eventData) throws JsonProcessingException {
    return objectMapper.writeValueAsString(generateGrnAck(eventData));
  }

  @Override
  @Transactional
  public EsbRcptDto generateGrnAck(String eventData) {
    EsbRcptDto esbRcptDto = composeEsbRcptDto(eventData);
    esbRcptDto.setEsbRcptMsgPreambleDtoList(composeEsbRcptMsgPreambleDto(eventData));
    esbRcptDto.setEsbRcptMainDtoList(composeEsbRcptMainDto(eventData));

    return esbRcptDto;
  }

  private EsbRcptDto composeEsbRcptDto(String eventData) {
    EsbRcptDto esbRcptDto = new EsbRcptDto();
    List<EsbRcptModel> esbRcptModelList = esbRcptMapper.selectByHjsNodeId(eventData);
    if (Objects.nonNull(esbRcptModelList) && esbRcptModelList.size() > 0) {
      BeanUtils.copyProperties(esbRcptModelList.get(0), esbRcptDto);
    }
    return esbRcptDto;
  }

  private List<EsbRcptMsgPreambleDto> composeEsbRcptMsgPreambleDto(String eventData) {
    List<EsbRcptMsgPreambleDto> esbRcptMsgPreambleDtoList = new ArrayList<>();
    List<EsbRcptMsgPreambleModel> esbRcptMsgPreambleModelList =
        esbRcptMsgPreambleMapper.selectByHjsParentId(eventData);
    if (Objects.nonNull(esbRcptMsgPreambleModelList) && esbRcptMsgPreambleModelList.size() > 0) {
      EsbRcptMsgPreambleDto esbRcptMsgPreambleDto = new EsbRcptMsgPreambleDto();
      BeanUtils.copyProperties(esbRcptMsgPreambleModelList.get(0), esbRcptMsgPreambleDto);
      esbRcptMsgPreambleDtoList.add(esbRcptMsgPreambleDto);
    }
    return esbRcptMsgPreambleDtoList;
  }

  private List<EsbRcptMainDto> composeEsbRcptMainDto(String eventData) {
    List<EsbRcptMainDto> esbRcptMainDtoList = new ArrayList<>();

    List<EsbRcptMainModel> esbRcptMainModelList = esbRcptMainMapper.selectByHjsParentId(eventData);
    List<EsbRcptSerialNoModel> esbRcptSerialNoModelList = esbRcptSerialNoMapper.selectByHjsParentId(eventData);

    if (Objects.nonNull(esbRcptMainModelList) && esbRcptMainModelList.size() > 0) {
      esbRcptMainModelList.stream().forEach(esbRcptMainModel -> {
        EsbRcptMainDto esbRcptMainDto = new EsbRcptMainDto();
        BeanUtils.copyProperties(esbRcptMainModel, esbRcptMainDto);
        esbRcptMainDtoList.add(esbRcptMainDto);

        if (Objects.nonNull(esbRcptSerialNoModelList) && esbRcptSerialNoModelList.size() > 0) {
          List<EsbRcptSerialNoDto> esbRcptSerialNoDtoList = new ArrayList<>();
          esbRcptSerialNoModelList.stream().filter(
              esbRcptSerialNoModel -> esbRcptMainDto.getHjsNodeId().equals(esbRcptSerialNoModel.getHjsParentId())
          ).forEach(esbRcptSerialNoModel -> {
            EsbRcptSerialNoDto esbRcptSerialNoDto = new EsbRcptSerialNoDto();
            BeanUtils.copyProperties(esbRcptSerialNoModel, esbRcptSerialNoDto);
            esbRcptSerialNoDtoList.add(esbRcptSerialNoDto);
          });
          esbRcptMainDto.setEsbRcptSerialNoDtoList(esbRcptSerialNoDtoList);
        }
      });
    }

    return esbRcptMainDtoList;
  }
}
