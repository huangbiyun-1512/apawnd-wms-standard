package com.maersk.apawnd.wms.standard.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.maersk.apawnd.wms.standard.dto.EsbRcptDto;

public interface EsbReceptionService {

  String generateJsonGrnAck(String eventData) throws JsonProcessingException;
  EsbRcptDto generateGrnAck(String eventData);

}
