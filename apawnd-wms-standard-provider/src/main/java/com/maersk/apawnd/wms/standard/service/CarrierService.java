package com.maersk.apawnd.wms.standard.service;

import com.maersk.apawnd.wms.standard.model.CarrierModel;

import java.util.List;

public interface CarrierService {

  List<CarrierModel> retrieve(String carrierName);
}
