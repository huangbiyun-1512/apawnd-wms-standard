package com.maersk.asia.wms.standard.dc.service;

import com.maersk.asia.wms.standard.dc.model.CarrierModel;

import java.util.List;

public interface CarrierService {

  List<CarrierModel> retrieveByCarrierName(String carrierName);
}
