package com.maersk.apawnd.wms.standard.service.impl;

import com.maersk.apawnd.commons.component.exception.BusinessException;
import com.maersk.apawnd.commons.component.util.ErrorUtil;
import com.maersk.apawnd.wms.standard.component.constant.MessageConstant;
import com.maersk.apawnd.wms.standard.component.enums.ClientControlC2Enum;
import com.maersk.apawnd.wms.standard.component.enums.ClientControlTypeEnum;
import com.maersk.apawnd.wms.standard.component.enums.EventNameEnum;
import com.maersk.apawnd.wms.standard.dto.EsbRcptDto;
import com.maersk.apawnd.wms.standard.dto.EsbRcptMainDto;
import com.maersk.apawnd.wms.standard.model.ClientControlModel;
import com.maersk.apawnd.wms.standard.model.EventQueueApiModel;
import com.maersk.apawnd.wms.standard.service.ClientControlService;
import com.maersk.apawnd.wms.standard.service.EsbReceptionService;
import com.maersk.apawnd.wms.standard.service.EventQueueService;
import com.maersk.apawnd.wms.standard.service.OutboundService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@Slf4j
@Service
public class OutboundServiceImpl implements OutboundService {

  private final EventQueueService eventQueueService;
  private final EsbReceptionService esbReceptionService;
  private final ClientControlService clientControlService;
  private final ErrorUtil errorUtil;
  private final RestTemplate restTemplate;

  public OutboundServiceImpl(
      EventQueueService eventQueueService,
      EsbReceptionService esbReceptionService,
      ClientControlService clientControlService,
      ErrorUtil errorUtil,
      RestTemplate restTemplate) {
    this.eventQueueService = eventQueueService;
    this.esbReceptionService = esbReceptionService;
    this.clientControlService = clientControlService;
    this.errorUtil = errorUtil;
    this.restTemplate = restTemplate;
  }

  @Override
  @Transactional
  public void sendAck(String eventName) {
    if (StringUtils.isNotBlank(eventName)) {
      List<EventQueueApiModel> eventQueueApiModelList = eventQueueService.retrieve(eventName);

      if (Objects.nonNull(eventQueueApiModelList) && eventQueueApiModelList.size() > 0) {
        eventQueueApiModelList.stream().forEach(eventQueueApiModel -> {
          try {
            eventQueueService.updateStatusStartByFifoSequence(eventQueueApiModel.getFifoSequence());
            EventNameEnum eventNameEnum = EventNameEnum.getByCode(eventQueueApiModel.getEventName());
            switch (eventNameEnum) {
              case EVENT_NAME_SCA_RCPT:
                sendGrnAck(eventQueueApiModel);
                break;
              default:
                throw new BusinessException(
                    errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_01_0101, eventQueueApiModel.getEventName()));
            }
            eventQueueService.updateStatusFinishedByFifoSequence(eventQueueApiModel.getFifoSequence());
          } catch (Exception e) {
            log.error(e.getMessage(), e);
            eventQueueService.updateErrorByFifoSequence(eventQueueApiModel, e.getMessage());
          }
        });

        eventQueueService.updateMonitorEndByEventName(eventName, "Success");
      }
    }
  }

  private void sendGrnAck(EventQueueApiModel eventQueueApiModel) {
    EsbRcptDto esbRcptDto =
        esbReceptionService.generateGrnAck(eventQueueApiModel.getEventData());

    if (Objects.nonNull(esbRcptDto) &&
        Objects.nonNull(esbRcptDto.getEsbRcptMainDtoList()) &&
        esbRcptDto.getEsbRcptMainDtoList().size() > 0) {
      EsbRcptMainDto esbRcptMainDto =
          esbRcptDto.getEsbRcptMainDtoList().get(0);
      postAckRequest(eventQueueApiModel.getEventName(),
          esbRcptMainDto.getWhId(),
          esbRcptMainDto.getClientCode(),
          esbRcptDto);
    }
  }

  private void postAckRequest(
      String eventName,
      String whId,
      String clientCode,
      Object payload) {
    String url = retrieveAckUrl(whId, clientCode, eventName);
    HttpEntity httpEntity = composeAckRequest(payload);
    restTemplate.postForEntity(url, httpEntity, String.class);
  }

  private String retrieveAckUrl(String whId, String clientCode, String eventName) {
    List<ClientControlModel> clientControlModelList =
        clientControlService.retrieve(
            whId,
            clientCode,
            ClientControlTypeEnum.CLIENT_CONTROL_TYPE_EDI_TRIGGER.getCode(),
            eventName);

    String url = "";
    if (Objects.nonNull(clientControlModelList) &&
        clientControlModelList.size() > 0) {
      if (!ClientControlC2Enum.CLIENT_CONTROL_C2_WEB_API.getCode().equals(clientControlModelList.get(0).getC2())) {
        throw new BusinessException(
            errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_01_0102));
      }
      url = clientControlModelList.get(0).getC3();
    }

    if (StringUtils.isEmpty(url)) {
      throw new BusinessException(
          errorUtil.build400ErrorList(MessageConstant.MESSAGE_KEY_E01_01_0103));
    }

    return url;
  }

  private HttpEntity composeAckRequest(Object payload) {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity httpEntity = new HttpEntity<>(payload, headers);
    return httpEntity;
  }
}
