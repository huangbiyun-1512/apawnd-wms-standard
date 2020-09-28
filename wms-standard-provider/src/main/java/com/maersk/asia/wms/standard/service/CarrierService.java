package com.maersk.asia.wms.standard.service;

import com.maersk.asia.wms.standard.model.CarrierModel;

import java.util.List;

public interface CarrierService {

  List<CarrierModel> retrieveByCarrierName(String carrierName);
}
