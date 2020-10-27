package com.maersk.apawnd.wms.standard.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.maersk.apawnd.commons.component.exception.BusinessException;
import com.maersk.apawnd.commons.component.util.MessageUtil;
import com.maersk.apawnd.wms.standard.component.constant.MessageConstant;
import com.maersk.apawnd.wms.standard.component.enums.ClientControlC2Enum;
import com.maersk.apawnd.wms.standard.component.enums.ClientControlTypeEnum;
import com.maersk.apawnd.wms.standard.component.enums.EventNameEnum;
import com.maersk.apawnd.wms.standard.dto.EsbRcptMainDto;
import com.maersk.apawnd.wms.standard.dto.GrnAckDto;
import com.maersk.apawnd.wms.standard.model.ApiEventMonitorModel;
import com.maersk.apawnd.wms.standard.model.ClientControlModel;
import com.maersk.apawnd.wms.standard.model.EventQueueApiModel;
import com.maersk.apawnd.wms.standard.service.ClientControlService;
import com.maersk.apawnd.wms.standard.service.EsbReceptionService;
import com.maersk.apawnd.wms.standard.service.EventQueueService;
import com.maersk.apawnd.wms.standard.service.OutboundService;
import com.maersk.apawnd.wms.standard.service.config.EventQueueServiceConfig;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class OutboundServiceImpl implements OutboundService {

  private final EventQueueService eventQueueService;
  private final EventQueueServiceConfig eventQueueServiceConfig;
  private final EsbReceptionService esbReceptionService;
  private final ClientControlService clientControlService;
  private final MessageUtil messageUtil;
  private final RestTemplate restTemplate;
  private final ObjectMapper objectMapper;

  private static Map<String, Instant> monitorExecuteTimeMap = new ConcurrentHashMap<>();

  public OutboundServiceImpl(
      EventQueueService eventQueueService,
      EventQueueServiceConfig eventQueueServiceConfig,
      EsbReceptionService esbReceptionService,
      ClientControlService clientControlService,
      MessageUtil messageUtil,
      RestTemplate restTemplate,
      ObjectMapper objectMapper) {
    this.eventQueueService = eventQueueService;
    this.eventQueueServiceConfig = eventQueueServiceConfig;
    this.esbReceptionService = esbReceptionService;
    this.clientControlService = clientControlService;
    this.messageUtil = messageUtil;
    this.restTemplate = restTemplate;
    this.objectMapper = objectMapper;
  }

  @Override
  @Transactional
  public void execute() {
    String threadId = String.valueOf(Thread.currentThread().getId());

    eventQueueService.initMonitorStatus(threadId);
    List<ApiEventMonitorModel> apiEventMonitorModelList =
        eventQueueService.retrieveEventMonitorByCurrentStatus(threadId);

    if (Objects.nonNull(apiEventMonitorModelList) && apiEventMonitorModelList.size() > 0) {
      apiEventMonitorModelList.stream().forEach(apiEventMonitorModel -> {
        try {
          String eventName = apiEventMonitorModel.getEventName();

          if (StringUtils.isNotEmpty(eventName) &&
              StringUtils.isNotEmpty(apiEventMonitorModel.getApplicationFolder())) {

            if (!monitorExecuteTimeMap.containsKey(eventName)) {
              monitorExecuteTimeMap.put(eventName, Instant.now());
            }

            Instant startTime = Instant.now();
            long interval =
                Duration.between(
                    monitorExecuteTimeMap.get(eventName), startTime).toSeconds();

            if (interval > eventQueueServiceConfig.getJobSkipSecond()) {
              monitorExecuteTimeMap.put(eventName, startTime);
              eventQueueService.updateMonitorStartByMonitorId(
                  threadId, apiEventMonitorModel.getMonitorId());
              sendAck(eventName);
            } else {
              eventQueueService.updateMonitorEndByEventName(eventName, "Skip");
            }
          } else {
            eventQueueService.updateMonitorEndByEventName(eventName, "Configuration Error");
          }
        } catch(Exception e) {
          eventQueueService.updateMonitorEndByEventName(
              apiEventMonitorModel.getEventName(), e.getMessage());
        }
      });
    }
  }

  private void sendAck(String eventName) {
    if (StringUtils.isNotBlank(eventName)) {
      List<EventQueueApiModel> eventQueueApiModelList = eventQueueService.retrieve(eventName);

      if (Objects.nonNull(eventQueueApiModelList) && eventQueueApiModelList.size() > 0) {
        for (EventQueueApiModel eventQueueApiModel : eventQueueApiModelList) {

          try {
            eventQueueService.updateStatusStartByFifoSequence(eventQueueApiModel.getFifoSequence());
            EventNameEnum eventNameEnum = EventNameEnum.getByCode(eventQueueApiModel.getEventName());

            if (Objects.isNull(eventNameEnum)) {
              throw new BusinessException(
                  messageUtil.getDetail(
                      MessageConstant.MESSAGE_KEY_E01_01_0101,
                      new Object[] {eventQueueApiModel.getEventName()}));
            }

            switch (eventNameEnum) {
              case EVENT_NAME_SCA_RCPT:
                sendGrnAck(eventQueueApiModel);
                break;
              default:
                throw new BusinessException(
                    messageUtil.getDetail(
                        MessageConstant.MESSAGE_KEY_E01_01_0101,
                        new Object[] {eventQueueApiModel.getEventName()}));
            }

            eventQueueService.updateStatusFinishedByFifoSequence(eventQueueApiModel.getFifoSequence());
            eventQueueService.updateMonitorEndByEventName(eventQueueApiModel.getEventName(), "Success");

          } catch (Exception e) {
            eventQueueService.updateErrorByFifoSequence(eventQueueApiModel, e.getMessage());
            eventQueueService.updateMonitorEndByEventName(eventQueueApiModel.getEventName(), e.getMessage());
          }

        }
      } else {
        eventQueueService.updateMonitorEndByEventName(eventName, "Success");
      }
    }
  }

  private void sendGrnAck(EventQueueApiModel eventQueueApiModel) throws JsonProcessingException {
    GrnAckDto grnAckDto =
        esbReceptionService.generateGrnAck(eventQueueApiModel.getEventData());

    if (Objects.nonNull(grnAckDto) &&
        Objects.nonNull(grnAckDto.getEsbRcptDto()) &&
        Objects.nonNull(grnAckDto.getEsbRcptDto().getEsbRcptMainCollectionDto()) &&
        Objects.nonNull(grnAckDto.getEsbRcptDto().getEsbRcptMainCollectionDto().getEsbRcptMainDtoList()) &&
        grnAckDto.getEsbRcptDto().getEsbRcptMainCollectionDto().getEsbRcptMainDtoList().size() > 0) {
      EsbRcptMainDto esbRcptMainDto =
          grnAckDto.getEsbRcptDto().getEsbRcptMainCollectionDto().getEsbRcptMainDtoList().get(0);
      postAckRequest(eventQueueApiModel.getEventName(),
          esbRcptMainDto.getWhId(),
          esbRcptMainDto.getClientCode(),
          grnAckDto);
    }
  }

  private void postAckRequest(
      String eventName,
      String whId,
      String clientCode,
      Object payload) throws JsonProcessingException {
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
            messageUtil.getDetail(
                MessageConstant.MESSAGE_KEY_E01_01_0102));
      }
      url = clientControlModelList.get(0).getC3();
    }

    if (StringUtils.isEmpty(url)) {
      throw new BusinessException(
          messageUtil.getDetail(
              MessageConstant.MESSAGE_KEY_E01_01_0103));
    }

    return url;
  }

  private HttpEntity composeAckRequest(Object payload) throws JsonProcessingException {
    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    HttpEntity httpEntity = new HttpEntity<>(objectMapper.writeValueAsBytes(payload), headers);
    return httpEntity;
  }
}
