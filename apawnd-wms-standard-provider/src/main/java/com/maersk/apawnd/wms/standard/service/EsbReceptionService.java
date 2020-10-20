package com.maersk.apawnd.wms.standard.service;

import com.maersk.apawnd.wms.standard.dto.EsbRcptDto;

public interface EsbReceptionService {

  EsbRcptDto generateGrnAck(String eventData);
}
