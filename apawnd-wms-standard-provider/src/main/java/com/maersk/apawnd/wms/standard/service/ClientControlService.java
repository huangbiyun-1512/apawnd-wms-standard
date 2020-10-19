package com.maersk.apawnd.wms.standard.service;

import com.maersk.apawnd.wms.standard.model.ClientControlModel;

import java.util.List;

public interface ClientControlService {

  List<ClientControlModel> retrieve(String whId, String clientCode, String controlType, String eventName);
}
