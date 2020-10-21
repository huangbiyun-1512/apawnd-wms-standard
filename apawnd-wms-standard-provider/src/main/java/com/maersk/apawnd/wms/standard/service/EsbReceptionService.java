package com.maersk.apawnd.wms.standard.service;

import com.maersk.apawnd.wms.standard.dto.GrnAckDto;

public interface EsbReceptionService {

  GrnAckDto generateGrnAck(String eventData);
}
